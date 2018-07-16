                                 String name, IRubyObject[] args, Block block, Block.Type blockType) {
        Instr[]   instrs    = interpreterContext.getInstructions();
        Object[]  temp      = interpreterContext.allocateTemporaryVariables();
        int       n         = instrs.length;
        int       ipc       = 0;
        Object    exception = null;

        if (interpreterContext.receivesKeywordArguments()) IRRuntimeHelpers.frobnicateKwargsArgument(context, interpreterContext.getRequiredArgsCount(), args);

        StaticScope currScope = interpreterContext.getStaticScope();
        DynamicScope currDynScope = context.getCurrentScope();
        IRScope scope = currScope.getIRScope();
        boolean      acceptsKeywordArgument = interpreterContext.receivesKeywordArguments();

        Stack<Integer> rescuePCs = new Stack<>();

        // Init profiling this scope
        boolean debug   = IRRuntimeHelpers.isDebug();
        boolean profile = IRRuntimeHelpers.inProfileMode();
        Integer scopeVersion = profile ? Profiler.initProfiling(scope) : 0;

        // Enter the looooop!
        while (ipc < n) {
            Instr instr = instrs[ipc];

            Operation operation = instr.getOperation();
            if (debug) {
                Interpreter.LOG.info("I: {" + ipc + "} ", instr + "; <#RPCs=" + rescuePCs.size() + ">");
                Interpreter.interpInstrsCount++;
            } else if (profile) {
                Profiler.instrTick(operation);
                Interpreter.interpInstrsCount++;
            }

            ipc++;

            try {
                switch (operation.opClass) {
                    case ARG_OP:
                        receiveArg(context, instr, operation, args, acceptsKeywordArgument, currDynScope, temp, exception, block);
                        break;
                    case CALL_OP:
                        if (profile) Profiler.updateCallSite(instr, scope, scopeVersion);
                        processCall(context, instr, operation, currDynScope, currScope, temp, self);
                        break;
                    case RET_OP:
                        return processReturnOp(context, instr, operation, currDynScope, temp, self, blockType, currScope);
                    case BRANCH_OP:
                        switch (operation) {
                            case JUMP:
                                JumpInstr jump = ((JumpInstr)instr);
                                if (jump.exitsExcRegion()) {
                                    rescuePCs.pop();
                                }
                                ipc = jump.getJumpTarget().getTargetPC();
                                break;
                            default:
                                ipc = instr.interpretAndGetNewIPC(context, currDynScope, currScope, self, temp, ipc);
                                break;
                        }
                        break;
                    case BOOK_KEEPING_OP:
                        if (operation == Operation.PUSH_BINDING) {
                            // IMPORTANT: Preserve this update of currDynScope.
                            // This affects execution of all instructions in this scope
                            // which will now use the updated value of currDynScope.
                            currDynScope = interpreterContext.newDynamicScope(context);
                            context.pushScope(currDynScope);
                        } else {
                            processBookKeepingOp(context, instr, operation, name, args, self, block, blockType, implClass, rescuePCs);
                        }
                        break;
                    case OTHER_OP:
                        processOtherOp(context, instr, operation, currDynScope, currScope, temp, self, blockType);
                        break;
                }
            } catch (Throwable t) {
                if (debug) extractToMethodToAvoidC2Crash(instr, t);

                if (rescuePCs.empty() || (t instanceof IRBreakJump && instr instanceof BreakInstr) ||
                        (t instanceof IRReturnJump && instr instanceof NonlocalReturnInstr)) {
                    ipc = -1;
                } else {
                    ipc = rescuePCs.pop();
                }

                if (debug) {
                    Interpreter.LOG.info("in : " + interpreterContext.getStaticScope().getIRScope() + ", caught Java throwable: " + t + "; excepting instr: " + instr);
                    Interpreter.LOG.info("ipc for rescuer: " + ipc);
                }

                if (ipc == -1) {
                    Helpers.throwException(t);
                } else {
                    exception = t;
                }
            }
        }

        // Control should never get here!
        throw context.runtime.newRuntimeError("BUG: interpreter fell through to end unexpectedly");
    }

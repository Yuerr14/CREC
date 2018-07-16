    public Operand buildVCall(VCallNode node, IR_Scope s) {
        List<Operand> args       = new ArrayList<Operand>(); args.add(s.getSelf());
        Variable      callResult = s.getNewTemporaryVariable();
        Instr      callInstr  = new CallInstr(callResult, new MethAddr(node.getName()), args.toArray(new Operand[args.size()]), null);
        s.addInstr(callInstr);
        return callResult;
    }

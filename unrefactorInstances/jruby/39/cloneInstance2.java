	public IRubyObject internalCall(ThreadContext context, IRubyObject receiver, RubyModule lastClass, String name, IRubyObject[] args, boolean noSuper, Block block) {
        IRuby runtime = context.getRuntime();
        arity.checkArity(runtime, args);
        
        assert receiver != null;
        assert args != null;
        assert method != null;
        
        Object[] methodArgs;
        if (!arity.isFixed()) {
            methodArgs = new Object[]{args, block};
        } else {
            methodArgs = new Object[args.length + 1];
            System.arraycopy(args, 0, methodArgs, 0, args.length);
            methodArgs[args.length] = block;
        }

        try {
            return (IRubyObject) method.invoke(receiver, methodArgs);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof RaiseException) {
                throw (RaiseException) e.getTargetException();
            } else if (e.getTargetException() instanceof JumpException) {
                throw (JumpException) e.getTargetException();
            } else if (e.getTargetException() instanceof ThreadKill) {
            	// allow it to bubble up
            	throw (ThreadKill) e.getTargetException();
            } else if (e.getTargetException() instanceof Exception) {
                if(e.getTargetException() instanceof MainExitException) {
                    throw (RuntimeException)e.getTargetException();
                }
                runtime.getJavaSupport().handleNativeException(e.getTargetException());
                return runtime.getNil();
            } else {
                throw (Error) e.getTargetException();
            }
        } catch (IllegalAccessException e) {
            StringBuffer message = new StringBuffer();
            message.append(e.getMessage());
            message.append(':');
            message.append(" methodName=").append(methodName);
            message.append(" recv=").append(receiver.toString());
            message.append(" type=").append(type.getName());
            message.append(" methodArgs=[");
            for (int i = 0; i < methodArgs.length; i++) {
                message.append(methodArgs[i]);
                message.append(' ');
            }
            message.append(']');
            assert false : message.toString();
            return null;
        } catch (final IllegalArgumentException e) {
/*            StringBuffer message = new StringBuffer();
            message.append(e.getMessage());
            message.append(':');
            message.append(" methodName=").append(methodName);
            message.append(" recv=").append(recv.toString());
            message.append(" type=").append(type.getName());
            message.append(" methodArgs=[");
            for (int i = 0; i < methodArgs.length; i++) {
                message.append(methodArgs[i]);
                message.append(' ');
            }
            message.append(']');*/
            assert false : e;
            return null;
        }
	}

            if(c == null) {
                String ret = getReturnName(method,new Class[]{IRubyObject[].class});
                ClassWriter cw = createCtor(mnamePath);
                MethodVisitor mv = startCall(cw);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitTypeInsn(CHECKCAST, "[" + IRUB_ID);
                mv.visitTypeInsn(CHECKCAST, "[" + IRUB_ID);
                mv.visitMethodInsn(INVOKEVIRTUAL, typePath, method, "([" + IRUB_ID + ")L" + ret + ";");
                mv.visitInsn(ARETURN);
                mv.visitMaxs(2, 3);
                c = endCall(cw,mv,mname);
            }

            if(c == null) {
                String ret = getReturnName(method,new Class[]{IRubyObject.class,IRubyObject[].class});
                ClassWriter cw = createCtor(mnamePath);
                MethodVisitor mv = startCallS(cw);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitTypeInsn(CHECKCAST, "[" + IRUB_ID);
                mv.visitTypeInsn(CHECKCAST, "[" + IRUB_ID);
                mv.visitMethodInsn(INVOKESTATIC, typePath, method, "(" + IRUB_ID + "[" + IRUB_ID + ")L"+ret+";");
                mv.visitInsn(ARETURN);
                mv.visitMaxs(2, 3);
                c = endCall(cw,mv,mname);
            }

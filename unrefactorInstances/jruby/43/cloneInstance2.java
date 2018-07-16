        if (iVisited.getName().equals("initialize")) {
            // got class, compare to Object
            getRubyClass();
            loadRuntime();
            invokeIRuby("getObject", "()Lorg/jruby/RubyClass;");
            // if class == Object
            mv.visitJumpInsn(Opcodes.IF_ACMPNE, l338);
            loadRuntime();
            // display warning about redefining Object#initialize
            invokeIRuby("getWarnings", "()Lorg/jruby/common/RubyWarnings;");
            mv.visitLdcInsn("redefining Object#initialize may cause infinite loop");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "org/jruby/common/RubyWarnings", "warn", "(Ljava/lang/String;)V");
        }

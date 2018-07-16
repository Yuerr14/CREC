        if (name.equals("initialize")) {
            Label notObjectClass = new Label();
    
            // get class, compare to Object
            getRubyClass();
            loadRuntime();
            invokeIRuby("getObject", "()Lorg/jruby/RubyClass;");
            
            // if class == Object
            mv.visitJumpInsn(Opcodes.IF_ACMPNE, notObjectClass);
            loadRuntime();
            
            // display warning about redefining Object#initialize
            invokeIRuby("getWarnings", "()Lorg/jruby/common/RubyWarnings;");
            mv.visitLdcInsn("redefining Object#initialize may cause infinite loop");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "org/jruby/common/RubyWarnings", "warn", "(Ljava/lang/String;)V");
        
            mv.visitLabel(notObjectClass);
        }

           if (methName != null) {
              System.out.println("################## After inline pass ##################");
              System.out.println("Asked to inline " + methName);
              scope.runCompilerPass(new org.jruby.compiler.ir.compiler_pass.InlineTest(methName));
              scope.runCompilerPass(new org.jruby.compiler.ir.compiler_pass.opts.LocalOptimizationPass());
              scope.runCompilerPass(new org.jruby.compiler.ir.compiler_pass.IR_Printer());
           }

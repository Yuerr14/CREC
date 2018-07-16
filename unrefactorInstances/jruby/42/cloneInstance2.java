            public void call(BodyCompiler context) {
                classBody.loadThreadContext();
                classBody.method.aload(StandardASMCompiler.SELF_INDEX); // module to run the module under passed in as self
                classBody.method.checkcast(p(RubyModule.class));

                // static scope
                script.getCacheCompiler().cacheStaticScope(classBody, staticScope);
                if (inspector.hasClosure() || inspector.hasScopeAwareMethods()) {
                    classBody.invokeThreadContext("preCompiledClass", sig(Void.TYPE, params(RubyModule.class, StaticScope.class)));
                } else {
                    classBody.invokeThreadContext("preCompiledClassDummyScope", sig(Void.TYPE, params(RubyModule.class, StaticScope.class)));
                }
            }

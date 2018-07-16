    public void testARGV_2() throws ScriptException {
        logger1.info("ScriptEngine.ARGV before initialization");
        ScriptEngine instance;
        synchronized(this) {
            System.setProperty("org.jruby.embed.localcontext.scope", "singlethread");
            System.setProperty("org.jruby.embed.localvariable.behavior", "transient");
            ScriptEngineManager manager = new ScriptEngineManager();
            instance = manager.getEngineByName("jruby");
        }
        instance.getContext().setErrorWriter(writer);
        Bindings bindings = instance.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put(ScriptEngine.ARGV, new String[]{"init params"});
        String script = "" +
//            "ARGV << 'foo' \n" +
            "if ARGV.length == 0\n" +
            "  raise 'Error No argv passed'\n" +
            "end";
        instance.eval(script);

        instance = null;
    }

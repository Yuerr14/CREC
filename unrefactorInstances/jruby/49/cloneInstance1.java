    public void testARGV() throws ScriptException {
        logger1.info("ScriptEngine.ARGV");
        ScriptEngine instance;
        synchronized(this) {
            System.setProperty("org.jruby.embed.localcontext.scope", "singlethread");
            System.setProperty("org.jruby.embed.localvariable.behavior", "transient");
            ScriptEngineManager manager = new ScriptEngineManager();
            instance = manager.getEngineByName("jruby");
        }
        instance.getContext().setErrorWriter(writer);
        String script = "" +
//            "ARGV << 'foo' \n" +
            "if ARGV.length == 0\n" +
            "  raise 'Error No argv passed'\n" +
            "end";
        instance.put(ScriptEngine.ARGV,new String[]{"one param"});
        instance.eval(script);

        instance = null;
    }

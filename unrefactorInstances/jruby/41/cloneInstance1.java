    public ByteList gets() {
        IRubyObject result = io.callMethod(io.getRuntime().getCurrentContext(), "gets");
        
        if (result.isNil()) return null;
        
        ByteList bytelist = result.convertToString().getByteList();
        offset += bytelist.getRealSize();
        bytelist.setEncoding(encoding);

        if (scriptLines != null) scriptLines.append(RubyString.newString(scriptLines.getRuntime(), bytelist));

        return bytelist;
    }

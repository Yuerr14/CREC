  public final static void writeUnescapedXML(Writer out, String tag, String val, Object... attrs) throws IOException {
    out.write('<');
    out.write(tag);
    for (int i=0; i<attrs.length; i++) {
      out.write(' ');
      out.write(attrs[i++].toString());
      out.write('=');
      out.write('"');
      out.write(attrs[i].toString());
      out.write('"');
    }
    if (val == null) {
      out.write('/');
      out.write('>');
    } else {
      out.write('>');
      out.write(val);
      out.write('<');
      out.write('/');
      out.write(tag);
      out.write('>');
    }
  }

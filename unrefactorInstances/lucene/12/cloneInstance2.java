  public void testMaxWordLengthWithSupplementary() throws IOException {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < 254; i++) {
      builder.append("A");
    }
    builder.append("\ud801\udc1c");
    LowerCaseTokenizer tokenizer = new LowerCaseTokenizer(
        TEST_VERSION_CURRENT, new StringReader(builder.toString() + builder.toString()));
    assertTokenStreamContents(tokenizer, new String[] {builder.toString().toLowerCase(), builder.toString().toLowerCase()});
  }

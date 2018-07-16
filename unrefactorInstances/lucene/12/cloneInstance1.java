  public void testMaxWordLength() throws IOException {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < 255; i++) {
      builder.append("A");
    }
    LowerCaseTokenizer tokenizer = new LowerCaseTokenizer(
        TEST_VERSION_CURRENT, new StringReader(builder.toString() + builder.toString()));
    assertTokenStreamContents(tokenizer, new String[] {builder.toString().toLowerCase(), builder.toString().toLowerCase()});
  }

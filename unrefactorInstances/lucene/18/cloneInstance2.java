  public void testPositionIncrementsWithOrig() throws IOException {
    SynonymMap map = new SynonymMap();

    boolean orig = true;
    boolean merge = true;

    // test that generated tokens start at the same offset as the original
    map.add(strings("a"), tokens("aa"), orig, merge);
    assertTokenizesTo(map, tokens("a,5"),
        new String[] { "a", "aa" },
        new int[] { 5, 0 });
    assertTokenizesTo(map, tokens("a,0"),
        new String[] { "a", "aa" },
        new int[] { 0, 0 });

    // test that offset of first replacement is ignored (always takes the orig offset)
    map.add(strings("b"), tokens("bb,100"), orig, merge);
    assertTokenizesTo(map, tokens("b,5"),
        new String[] { "b", "bb" },
        new int[] { 5, 0 });
    assertTokenizesTo(map, tokens("b,0"),
        new String[] { "b", "bb" },
        new int[] { 0, 0 });

    // test that subsequent tokens are adjusted accordingly
    map.add(strings("c"), tokens("cc,100 c2,2"), orig, merge);
    assertTokenizesTo(map, tokens("c,5"),
        new String[] { "c", "cc", "c2" },
        new int[] { 5, 0, 2 });
    assertTokenizesTo(map, tokens("c,0"),
        new String[] { "c", "cc", "c2" },
        new int[] { 0, 0, 2 });
  }

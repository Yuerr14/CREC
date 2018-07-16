  public void testPositionIncrements() throws IOException {
    SynonymMap map = new SynonymMap();

    boolean orig = false;
    boolean merge = true;

    // test that generated tokens start at the same posInc as the original
    map.add(strings("a"), tokens("aa"), orig, merge);
    assertTokenizesTo(map, tokens("a,5"), 
        new String[] { "aa" },
        new int[] { 5 });
    assertTokenizesTo(map, tokens("a,0"),
        new String[] { "aa" },
        new int[] { 0 });

    // test that offset of first replacement is ignored (always takes the orig offset)
    map.add(strings("b"), tokens("bb,100"), orig, merge);
    assertTokenizesTo(map, tokens("b,5"),
        new String[] { "bb" },
        new int[] { 5 });
    assertTokenizesTo(map, tokens("b,0"),
        new String[] { "bb" },
        new int[] { 0 });

    // test that subsequent tokens are adjusted accordingly
    map.add(strings("c"), tokens("cc,100 c2,2"), orig, merge);
    assertTokenizesTo(map, tokens("c,5"),
        new String[] { "cc", "c2" },
        new int[] { 5, 2 });
    assertTokenizesTo(map, tokens("c,0"),
        new String[] { "cc", "c2" },
        new int[] { 0, 2 });
  }

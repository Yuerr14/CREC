  public void testBoolean2() throws Exception {
    BooleanQuery query = new BooleanQuery();
    query.add(new TermQuery(new Term("field", "sevento")), BooleanClause.Occur.MUST);
    query.add(new TermQuery(new Term("field", "sevenly")), BooleanClause.Occur.MUST);
    checkHits(query, new int[] {});
  }

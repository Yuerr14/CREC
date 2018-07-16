  public void testNpeInSpanNearInSpanFirstInSpanNot() throws Exception {
    int n = 5;
    SpanTermQuery hun = new SpanTermQuery(new Term("field", "hundred"));
    SpanTermQuery term40 = new SpanTermQuery(new Term("field", "forty"));
    SpanTermQuery term40c = (SpanTermQuery)term40.clone();

    SpanFirstQuery include = new SpanFirstQuery(term40, n);
    SpanNearQuery near = new SpanNearQuery(new SpanQuery[]{hun, term40c},
                                           n-1, true);
    SpanFirstQuery exclude = new SpanFirstQuery(near, n-1);
    SpanNotQuery q = new SpanNotQuery(include, exclude);
    
    checkHits(q, new int[]{40,41,42,43,44,45,46,47,48,49});
    
  }

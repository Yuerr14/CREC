  public void reSetNorms(String field) throws IOException {
    String fieldName = field.intern();
    int[] termCounts = new int[0];
    byte[] fakeNorms = new byte[0];
    
    IndexReader reader = null;
    TermEnum termEnum = null;
    TermDocs termDocs = null;
    try {
      reader = IndexReader.open(dir);
      termCounts = new int[reader.maxDoc()];
      // if we are killing norms, get fake ones
      if (sim == null)
        fakeNorms = SegmentReader.createFakeNorms(reader.maxDoc());
      try {
        termEnum = reader.terms(new Term(field,""));
        try {
          termDocs = reader.termDocs();
          do {
            Term term = termEnum.term();
            if (term != null && term.field().equals(fieldName)) {
              termDocs.seek(termEnum.term());
              while (termDocs.next()) {
                termCounts[termDocs.doc()] += termDocs.freq();
              }
            }
          } while (termEnum.next());
          
        } finally {
          if (null != termDocs) termDocs.close();
        }
      } finally {
        if (null != termEnum) termEnum.close();
      }
    } finally {
      if (null != reader) reader.close();
    }
    
    try {
      reader = IndexReader.open(dir); 
      for (int d = 0; d < termCounts.length; d++) {
        if (! reader.isDeleted(d)) {
          if (sim == null)
            reader.setNorm(d, fieldName, fakeNorms[0]);
          else
            reader.setNorm(d, fieldName, sim.encodeNorm(sim.lengthNorm(fieldName, termCounts[d])));
        }
      }
      
    } finally {
      if (null != reader) reader.close();
    }
  }

  public void reSetNorms(String field) throws IOException {
    String fieldName = field.intern();
    int[] termCounts = new int[0];
    
    IndexReader reader = null;
    TermEnum termEnum = null;
    TermDocs termDocs = null;
    try {
      reader = IndexReader.open(dir);
      termCounts = new int[reader.maxDoc()];
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
          byte norm = sim.encodeNorm(sim.lengthNorm(fieldName, termCounts[d]));
          reader.setNorm(d, fieldName, norm);
        }
      }
    } finally {
      if (null != reader) reader.close();
    }
  }

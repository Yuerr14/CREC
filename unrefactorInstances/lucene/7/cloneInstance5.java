                                            String secondEnd) throws Exception {
    RAMDirectory ramDir = new RAMDirectory();
    IndexWriter writer = new IndexWriter(ramDir, new IndexWriterConfig(
        TEST_VERSION_CURRENT, analyzer));
    Document doc = new Document();
    doc.add(new Field("content", "\u0633\u0627\u0628", 
                      Field.Store.YES, Field.Index.ANALYZED));
    doc.add(new Field("body", "body",
                      Field.Store.YES, Field.Index.NOT_ANALYZED));
    writer.addDocument(doc);
    writer.close();
    IndexSearcher searcher = new IndexSearcher(ramDir, true);
    Query query = new TermQuery(new Term("body","body"));

    // Unicode order would include U+0633 in [ U+062F - U+0698 ], but Farsi
    // orders the U+0698 character before the U+0633 character, so the single
    // index Term below should NOT be returned by a TermRangeFilter with a Farsi
    // Collator (or an Arabic one for the case when Farsi searcher not
    // supported).
    ScoreDoc[] result = searcher.search
      (query, new TermRangeFilter("content", firstBeg, firstEnd, true, true), 1).scoreDocs;
    assertEquals("The index Term should not be included.", 0, result.length);

    result = searcher.search
      (query, new TermRangeFilter("content", secondBeg, secondEnd, true, true), 1).scoreDocs;
    assertEquals("The index Term should be included.", 1, result.length);

    searcher.close();
  }

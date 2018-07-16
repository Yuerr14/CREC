      String firstEnd, String secondBeg, String secondEnd) throws Exception {

    RAMDirectory farsiIndex = new RAMDirectory();
    IndexWriter writer = new IndexWriter(farsiIndex, new IndexWriterConfig(
        TEST_VERSION_CURRENT, analyzer));
    Document doc = new Document();
    doc.add(new Field("content", "\u0633\u0627\u0628", 
                      Field.Store.YES, Field.Index.ANALYZED));
    doc.add(new Field("body", "body",
                      Field.Store.YES, Field.Index.NOT_ANALYZED));
    writer.addDocument(doc);
    writer.close();

    IndexReader reader = IndexReader.open(farsiIndex, true);
    IndexSearcher search = new IndexSearcher(reader);
        
    // Unicode order would include U+0633 in [ U+062F - U+0698 ], but Farsi
    // orders the U+0698 character before the U+0633 character, so the single
    // index Term below should NOT be returned by a TermRangeQuery
    // with a Farsi Collator (or an Arabic one for the case when Farsi is 
    // not supported).
    Query csrq 
      = new TermRangeQuery("content", firstBeg, firstEnd, true, true);
    ScoreDoc[] result = search.search(csrq, null, 1000).scoreDocs;
    assertEquals("The index Term should not be included.", 0, result.length);

    csrq = new TermRangeQuery
      ("content", secondBeg, secondEnd, true, true);
    result = search.search(csrq, null, 1000).scoreDocs;
    assertEquals("The index Term should be included.", 1, result.length);
    search.close();
  }

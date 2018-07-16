    public void testFarsi() throws Exception {
            
        /* build an index */
      RAMDirectory farsiIndex = new RAMDirectory();
      IndexWriter writer = new IndexWriter(farsiIndex, new IndexWriterConfig(
          TEST_VERSION_CURRENT, new SimpleAnalyzer(
              TEST_VERSION_CURRENT)));
        Document doc = new Document();
        doc.add(new Field("content","\u0633\u0627\u0628", 
                          Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("body", "body",
                          Field.Store.YES, Field.Index.NOT_ANALYZED));
        writer.addDocument(doc);
            
        writer.optimize();
        writer.close();

        IndexReader reader = IndexReader.open(farsiIndex, true);
        IndexSearcher search = new IndexSearcher(reader);
        Query q = new TermQuery(new Term("body","body"));

        // Neither Java 1.4.2 nor 1.5.0 has Farsi Locale collation available in
        // RuleBasedCollator.  However, the Arabic Locale seems to order the Farsi
        // characters properly.
        Collator collator = Collator.getInstance(new Locale("ar"));
        
        // Unicode order would include U+0633 in [ U+062F - U+0698 ], but Farsi
        // orders the U+0698 character before the U+0633 character, so the single
        // index Term below should NOT be returned by a TermRangeFilter with a Farsi
        // Collator (or an Arabic one for the case when Farsi is not supported).
        int numHits = search.search
            (q, new TermRangeFilter("content", "\u062F", "\u0698", T, T, collator), 1000).totalHits;
        assertEquals("The index Term should not be included.", 0, numHits);

        numHits = search.search
            (q, new TermRangeFilter("content", "\u0633", "\u0638", T, T, collator), 1000).totalHits;
        assertEquals("The index Term should be included.", 1, numHits);
        search.close();
    }

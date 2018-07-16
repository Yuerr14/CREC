    public void testDanish() throws Exception {
            
        /* build an index */
        RAMDirectory danishIndex = new RAMDirectory();
        IndexWriter writer = new IndexWriter(danishIndex, new IndexWriterConfig(
            TEST_VERSION_CURRENT, new SimpleAnalyzer(
                TEST_VERSION_CURRENT)));
        // Danish collation orders the words below in the given order
        // (example taken from TestSort.testInternationalSort() ).
        String[] words = { "H\u00D8T", "H\u00C5T", "MAND" };
        for (int docnum = 0 ; docnum < words.length ; ++docnum) {   
            Document doc = new Document();
            doc.add(new Field("content", words[docnum], 
                              Field.Store.YES, Field.Index.NOT_ANALYZED));
            doc.add(new Field("body", "body",
                              Field.Store.YES, Field.Index.NOT_ANALYZED));
            writer.addDocument(doc);
        }
        writer.optimize();
        writer.close();

        IndexReader reader = IndexReader.open(danishIndex, true);
        IndexSearcher search = new IndexSearcher(reader);
        Query q = new TermQuery(new Term("body","body"));

        Collator collator = Collator.getInstance(new Locale("da", "dk"));

        // Unicode order would not include "H\u00C5T" in [ "H\u00D8T", "MAND" ],
        // but Danish collation does.
        int numHits = search.search
            (q, new TermRangeFilter("content", "H\u00D8T", "MAND", F, F, collator), 1000).totalHits;
        assertEquals("The index Term should be included.", 1, numHits);

        numHits = search.search
            (q, new TermRangeFilter("content", "H\u00C5T", "MAND", F, F, collator), 1000).totalHits;
        assertEquals
            ("The index Term should not be included.", 0, numHits);
        search.close();
    }

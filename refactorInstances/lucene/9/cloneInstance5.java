  public void testAllSegmentsLarge() throws Exception {
    Directory dir = new RAMDirectory();
    
    IndexWriterConfig conf = newIndexWriterConfig(TEST_VERSION_CURRENT, null);
    conf.setMergePolicy(NoMergePolicy.COMPOUND_FILES);
    IndexWriter writer = new IndexWriter(dir, conf);
    
    addDocs(writer, 3);
    addDocs(writer, 3);
    addDocs(writer, 3);
    
    writer.close();
    
    conf = newIndexWriterConfig(TEST_VERSION_CURRENT, null);
    LogMergePolicy lmp = new LogDocMergePolicy();
    lmp.setMaxMergeDocs(2);
    conf.setMergePolicy(lmp);
    
    writer = new IndexWriter(dir, conf);
    writer.optimize();
    writer.close();
    
    SegmentInfos sis = new SegmentInfos();
    sis.read(dir);
    assertEquals(3, sis.size());
  }

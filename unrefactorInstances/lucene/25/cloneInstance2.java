  private Status.StoredFieldStatus testStoredFields(SegmentInfo info, SegmentReader reader, NumberFormat format) {
    final Status.StoredFieldStatus status = new Status.StoredFieldStatus();

    try {
      if (infoStream != null) {
        infoStream.print("    test: stored fields.......");
      }

      // Scan stored fields for all documents
      for (int j = 0; j < info.docCount; ++j) {
        if (!reader.isDeleted(j)) {
          status.docCount++;
          Document doc = reader.document(j);
          status.totFields += doc.getFields().size();
        }
      }      

      // Validate docCount
      if (status.docCount != reader.numDocs()) {
        throw new RuntimeException("docCount=" + status.docCount + " but saw " + status.docCount + " undeleted docs");
      }

      msg("OK [" + status.totFields + " total field count; avg " + 
          format.format((((float) status.totFields)/status.docCount)) + " fields per doc]");      
    } catch (Throwable e) {
      msg("ERROR [" + String.valueOf(e.getMessage()) + "]");
      status.error = e;
      if (infoStream != null) {
        e.printStackTrace(infoStream);
      }
    }

    return status;
  }

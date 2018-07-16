  public void write(Directory dir, SegmentInfo si, FieldInfos fis, IOContext ioContext) throws IOException {
    final String fileName = IndexFileNames.segmentFileName(si.name, "", Lucene50SegmentInfoFormat.SI_EXTENSION);
    si.addFile(fileName);

    final IndexOutput output = dir.createOutput(fileName, ioContext);

    boolean success = false;
    try {
      CodecUtil.writeHeader(output, Lucene50SegmentInfoFormat.CODEC_NAME, Lucene50SegmentInfoFormat.VERSION_CURRENT);
      Version version = si.getVersion();
      if (version.major < 5) {
        throw new IllegalArgumentException("invalid major version: should be >= 5 but got: " + version.major + " segment=" + si);
      }
      // Write the Lucene version that created this segment, since 3.1
      output.writeString(version.toString());
      output.writeInt(si.getDocCount());

      output.writeByte((byte) (si.getUseCompoundFile() ? SegmentInfo.YES : SegmentInfo.NO));
      output.writeStringStringMap(si.getDiagnostics());
      output.writeStringSet(si.files());
      output.writeString(si.getId());
      CodecUtil.writeFooter(output);
      success = true;
    } finally {
      if (!success) {
        IOUtils.closeWhileHandlingException(output);
        // TODO: are we doing this outside of the tracking wrapper? why must SIWriter cleanup like this?
        IOUtils.deleteFilesIgnoringExceptions(si.dir, fileName);
      } else {
        output.close();
      }
    }
  }

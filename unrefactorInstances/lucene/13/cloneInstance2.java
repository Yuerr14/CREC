  public DocValues getValues(IndexReader reader) throws IOException {
    final int[] arr = FieldCache.DEFAULT.getStringIndex(reader, field).order;
    return new DocValues() {
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#floatVal(int) */
      @Override
      public float floatVal(int doc) {
        return arr[doc];
      }
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#strVal(int) */
      @Override
      public String strVal(int doc) {
        // the string value of the ordinal, not the string itself
        return Integer.toString(arr[doc]);
      }
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#toString(int) */
      @Override
      public String toString(int doc) {
        return description() + '=' + intVal(doc);
      }
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#getInnerArray() */
      @Override
      Object getInnerArray() {
        return arr;
      }
    };
  }

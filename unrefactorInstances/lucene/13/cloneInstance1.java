  public DocValues getCachedFieldValues (FieldCache cache, String field, IndexReader reader) throws IOException {
    final byte[] arr = cache.getBytes(reader, field, parser);
    return new DocValues() {
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#floatVal(int) */
      @Override
      public float floatVal(int doc) { 
        return arr[doc]; 
      }
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#intVal(int) */
      @Override
      public  int intVal(int doc) { 
        return arr[doc]; 
      }
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#toString(int) */
      @Override
      public String toString(int doc) { 
        return  description() + '=' + intVal(doc);  
      }
      /*(non-Javadoc) @see org.apache.lucene.search.function.DocValues#getInnerArray() */
      @Override
      Object getInnerArray() {
        return arr;
      }
    };
  }

    public FieldComparator setNextReader(AtomicReaderContext context) throws IOException {
      final int docBase = context.docBase;
      termsIndex = FieldCache.DEFAULT.getTermsIndex(context.reader, field);
      final PackedInts.Reader docToOrd = termsIndex.getDocToOrd();
      FieldComparator perSegComp = null;
      if (docToOrd.hasArray()) {
        final Object arr = docToOrd.getArray();
        if (arr instanceof byte[]) {
          perSegComp = new ByteOrdComparator((byte[]) arr, termsIndex, docBase);
        } else if (arr instanceof short[]) {
          perSegComp = new ShortOrdComparator((short[]) arr, termsIndex, docBase);
        } else if (arr instanceof int[]) {
          perSegComp = new IntOrdComparator((int[]) arr, termsIndex, docBase);
        }
        // Don't specialize the long[] case since it's not
        // possible, ie, worse case is MAX_INT-1 docs with
        // every one having a unique value.
      }
      if (perSegComp == null) {
        perSegComp = new AnyOrdComparator(docToOrd, termsIndex, docBase);
      }

      currentReaderGen++;
      if (bottomSlot != -1) {
        perSegComp.setBottom(bottomSlot);
      }

      return perSegComp;
    }

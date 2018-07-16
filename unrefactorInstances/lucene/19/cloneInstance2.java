  public static FieldComparator createComparator(IndexReader reader, TermOrdValComparator_SML parent) throws IOException {
    parent.termsIndex = FieldCache.DEFAULT.getTermsIndex(reader, parent.field);
    final PackedInts.Reader docToOrd = parent.termsIndex.getDocToOrd();
    PerSegmentComparator perSegComp = null;
    if (docToOrd.hasArray()) {
      final Object arr = docToOrd.getArray();
      if (arr instanceof byte[]) {
        perSegComp = new ByteOrdComparator((byte[]) arr, parent);
      } else if (arr instanceof short[]) {
        perSegComp = new ShortOrdComparator((short[]) arr, parent);
      } else if (arr instanceof int[]) {
        perSegComp = new IntOrdComparator((int[]) arr, parent);
      }
    }

    if (perSegComp == null) {
      perSegComp = new AnyOrdComparator(docToOrd, parent);
    }

    if (perSegComp.bottomSlot != -1) {
      perSegComp.setBottom(perSegComp.bottomSlot);
    }

    parent.current = perSegComp;
    return perSegComp;
  }

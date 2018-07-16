          if (matchingDocs.totalHits < numSegOrds/10) {
            // Remap every ord to global ord as we iterate:
            int doc = 0;
            while (doc < maxDoc && (doc = matchingDocs.bits.nextSetBit(doc)) != -1) {
              segValues.setDocument(doc);
              int term = (int) segValues.nextOrd();
              while (term != SortedSetDocValues.NO_MORE_ORDS) {
                counts[(int) ordinalMap.getGlobalOrd(segOrd, term)]++;
                term = (int) segValues.nextOrd();
              }
              ++doc;
            }
          } else {

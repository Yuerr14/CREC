        } else {
          // No ord mapping (e.g., single segment index):
          // just aggregate directly into counts:

          int doc = 0;
          while (doc < maxDoc && (doc = matchingDocs.bits.nextSetBit(doc)) != -1) {
            segValues.setDocument(doc);
            int term = (int) segValues.nextOrd();
            while (term != SortedSetDocValues.NO_MORE_ORDS) {
              counts[term]++;
              term = (int) segValues.nextOrd();
            }
            ++doc;
          }
        }

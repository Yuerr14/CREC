    if (shouldFilters != null) {
      for (int i = 0; i < shouldFilters.size(); i++) {
        if (res == null) {
          res = new OpenBitSetDISI(getDISI(shouldFilters, i, reader), reader.maxDoc());
        } else { 
          DocIdSet dis = shouldFilters.get(i).getDocIdSet(reader);
          if(dis instanceof OpenBitSet) {
            // optimized case for OpenBitSets
            res.or((OpenBitSet) dis);
          } else {
            res.inPlaceOr(getDISI(shouldFilters, i, reader));
          }
        }
      }
    }

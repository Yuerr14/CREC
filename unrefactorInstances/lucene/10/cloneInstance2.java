    if (mustFilters!=null) {
      for (int i = 0; i < mustFilters.size(); i++) {
        if (res == null) {
          res = new OpenBitSetDISI(getDISI(mustFilters, i, reader), reader.maxDoc());
        } else {
          DocIdSet dis = mustFilters.get(i).getDocIdSet(reader);
          if(dis instanceof OpenBitSet) {
            // optimized case for OpenBitSets
            res.and((OpenBitSet) dis);
          } else {
            res.inPlaceAnd(getDISI(mustFilters, i, reader));
          }
        }
      }
    }

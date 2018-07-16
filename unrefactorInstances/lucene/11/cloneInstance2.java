  private boolean isSparse() {
    // note: order of comparisons below set to favor smaller values (no binary range search.)
    // note: adding 4 because we start with ((int) -1) to indicate d-gaps format.
    // note: we write the d-gap for the byte number, and the byte (bits[i]) itself, therefore
    //       multiplying count by (8+8) or (8+16) or (8+24) etc.:
    //       - first 8 for writing bits[i] (1 byte vs. 1 bit), and 
    //       - second part for writing the byte-number d-gap as vint. 
    // note: factor is for read/write of byte-arrays being faster than vints.  
    int factor = 10;  
    if (bits.length < (1<< 7)) return factor * (4 + (8+ 8)*count()) < size();
    if (bits.length < (1<<14)) return factor * (4 + (8+16)*count()) < size();
    if (bits.length < (1<<21)) return factor * (4 + (8+24)*count()) < size();
    if (bits.length < (1<<28)) return factor * (4 + (8+32)*count()) < size();
    return                            factor * (4 + (8+40)*count()) < size();
  }

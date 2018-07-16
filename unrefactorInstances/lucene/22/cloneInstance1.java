   } else {

     int[] result = new int[size()];
     int resultCount=0;
     for (int i=0; i<table.length; i++) {
       int id=table[i];
       if (id >= 0 && other.exists(id)) {
         result[resultCount++]=id;
       }
     }
     return new HashDocSet(result,0,resultCount);
   }

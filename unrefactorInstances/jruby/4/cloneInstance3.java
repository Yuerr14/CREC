                     if(sampleOff<0 || sampleLen<0){ 
                     //the group is not def., as in the case of '(\w+)\1'
                     //treat as usual BACKTRACK_MIN
                        cnt--;
                        i--;
                        actual.cnt=cnt;
                        actual.index=i;
                        actual.term=term;
                        actual=(top=actual).on;
                        if(actual==null){
                           actual=new SearchEntry();
                           top.on=actual;
                           actual.sub=top;
                        }
                        term=term.next;
                        continue;
                     }

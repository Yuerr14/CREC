                  if(cnt>term.minCount){
                     regLen=actual.regLen;
                     cnt--;
                     i-=regLen;
                     actual.cnt=cnt;
                     actual.index=i;
                     actual.term=term;
                     //actual.regLen=regLen;
                     actual=(top=actual).on;
                     if(actual==null){
                        actual=new SearchEntry();
                        top.on=actual;
                        actual.sub=top;
                     }
                     term=term.next;
                     continue;
                  }

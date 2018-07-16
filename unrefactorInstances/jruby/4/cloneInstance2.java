                  if(cnt>term.minCount){
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

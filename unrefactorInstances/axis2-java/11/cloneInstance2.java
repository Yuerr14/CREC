               try {
               int event = reader.getEventType();

              //event better be a START_ELEMENT. if not we should go up to the start element here
               while (event!= javax.xml.stream.XMLStreamReader.START_ELEMENT) {
                   event = reader.next();
               }

               
               if (!MY_QNAME.equals(reader.getName())){
                           throw new Exception("Wrong QName");
               }
               
                       org.apache.axis2.databinding.utils.SimpleElementReaderStateMachine stateMachine1
                         = new org.apache.axis2.databinding.utils.SimpleElementReaderStateMachine();
                       javax.xml.namespace.QName startQname1 = new javax.xml.namespace.QName(
                                            "http://test",
                                           "input");
                       stateMachine1.setElementNameToTest(startQname1);
                       stateMachine1.setNillable();
                       stateMachine1.read(reader);
                       object.setInput(
                         stateMachine1.getText()==null?null:
                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                  stateMachine1.getText()));
                             
               } catch (javax.xml.stream.XMLStreamException e) {

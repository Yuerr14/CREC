            public BytesRefArrayRef getValues(int docId) {
                IntArrayRef ords = ordinals.getOrds(docId);
                int size = ords.size();
                if (size == 0) return BytesRefArrayRef.EMPTY;

                arrayScratch.reset(size);
                for (int i = ords.start; i < ords.end; i++) {
                    final BytesRef bytesRef = new BytesRef();
                    bytes.fill(bytesRef, termOrdToBytesOffset.get(ords.values[i]));
                    arrayScratch.values[arrayScratch.end++] = bytesRef;
                }
                return arrayScratch;
            }

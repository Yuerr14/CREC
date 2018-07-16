                } else if (fieldName.equals("includes")) {
                    List<Object> values = (List<Object>) fieldNode;
                    String[] includes = new String[values.size()];
                    for (int i = 0; i < includes.length; i++) {
                        includes[i] = values.get(i).toString();
                    }
                    builder.includes(includes);
                } else if (fieldName.equals("excludes")) {

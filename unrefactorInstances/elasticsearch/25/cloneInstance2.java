                } else if (fieldName.equals("excludes")) {
                    List<Object> values = (List<Object>) fieldNode;
                    String[] excludes = new String[values.size()];
                    for (int i = 0; i < excludes.length; i++) {
                        excludes[i] = values.get(i).toString();
                    }
                    builder.excludes(excludes);
                }

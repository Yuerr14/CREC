                                } else if (token.isValue()) {
                                    Point point = new Point();
                                    String value = parser.text();
                                    int comma = value.indexOf(',');
                                    if (comma != -1) {
                                        point.lat = Double.parseDouble(value.substring(0, comma).trim());
                                        point.lon = Double.parseDouble(value.substring(comma + 1).trim());
                                    } else {
                                        double[] values = GeoHashUtils.decode(value);
                                        point.lat = values[0];
                                        point.lon = values[1];
                                    }
                                    points.add(point);
                                }

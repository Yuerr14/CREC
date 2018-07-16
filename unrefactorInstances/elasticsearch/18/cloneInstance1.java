                } else if (currentFieldName.endsWith(GeoPointFieldMapper.Names.LAT_SUFFIX)) {
                    String maybeFieldName = currentFieldName.substring(0,
                            currentFieldName.length() - GeoPointFieldMapper.Names.LAT_SUFFIX.length());
                    if (fieldName == null || fieldName.equals(maybeFieldName)) {
                        fieldName = maybeFieldName;
                    } else {
                        throw new ParsingException(parser.getTokenLocation(), "[" + GeoDistanceRangeQueryBuilder.NAME +
                                "] field name already set to [" + fieldName + "] but found [" + currentFieldName + "]");
                    }
                    if (point == null) {
                        point = new GeoPoint();
                    }
                    point.resetLat(parser.doubleValue());
                } else if (currentFieldName.endsWith(GeoPointFieldMapper.Names.LON_SUFFIX)) {

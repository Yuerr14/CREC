                } else if (currentFieldName.endsWith(GeoPointFieldMapper.Names.LON_SUFFIX)) {
                    String maybeFieldName = currentFieldName.substring(0,
                            currentFieldName.length() - GeoPointFieldMapper.Names.LON_SUFFIX.length());
                    if (fieldName == null || fieldName.equals(maybeFieldName)) {
                        fieldName = maybeFieldName;
                    } else {
                        throw new ParsingException(parser.getTokenLocation(), "[" + GeoDistanceRangeQueryBuilder.NAME +
                                "] field name already set to [" + fieldName + "] but found [" + currentFieldName + "]");
                    }
                    if (point == null) {
                        point = new GeoPoint();
                    }
                    point.resetLon(parser.doubleValue());
                } else if (parseContext.getParseFieldMatcher().match(currentFieldName, NAME_FIELD)) {

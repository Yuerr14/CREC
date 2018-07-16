                        minorTick++) {
                    long minorTickTime = currentTickTime
                            + (nextTickTime - currentTickTime)
                            * minorTick / minorTickSpaces;
                    if (getRange().contains(minorTickTime)
                            && (!isHiddenValue(minorTickTime))) {
                        result.add(new DateTick(TickType.MINOR,
                                new Date(minorTickTime), "",
                                TextAnchor.TOP_CENTER, TextAnchor.CENTER,
                                0.0));
                    }
                }

            for (int minorTick = 1; minorTick < minorTickSpaces; minorTick++) {
                long minorTickTime = lowestTickTime - distance
                        * minorTick / minorTickSpaces;
                if (minorTickTime > 0 && getRange().contains(minorTickTime)
                        && (!isHiddenValue(minorTickTime))) {
                    result.add(new DateTick(TickType.MINOR,
                            new Date(minorTickTime), "", TextAnchor.TOP_CENTER,
                            TextAnchor.CENTER, 0.0));
                }
            }

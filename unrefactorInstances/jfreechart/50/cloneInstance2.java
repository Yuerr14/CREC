        while (current <= end) {
            double v = calculateValue(current);
            if (range.contains(v)) {
                ticks.add(new NumberTick(TickType.MAJOR, v, createTickLabel(v), 
                        textAnchor, TextAnchor.CENTER, 0.0));
            }
            // add minor ticks (for gridlines)
            double next = Math.pow(this.base, current 
                    + this.tickUnit.getSize());
            for (int i = 1; i < this.minorTickCount; i++) {
                double minorV = v + i * ((next - v) / this.minorTickCount);
                if (range.contains(minorV)) {
                    ticks.add(new NumberTick(TickType.MINOR, minorV, "", 
                            textAnchor, TextAnchor.CENTER, 0.0));
                }
            }
            current = current + this.tickUnit.getSize();
        }

        for (int i = 0; i < row; i++) {
            Number v = dataset.getValue(i, column);
            if (v != null && isSeriesVisible(i)) {
                double d = v.doubleValue();
                if (this.renderAsPercentages) {
                    d = d / total;
                }
                if (d > 0) {
                    positiveBase = positiveBase + d;
                }
                else {
                    negativeBase = negativeBase + d;
                }
            }
        }

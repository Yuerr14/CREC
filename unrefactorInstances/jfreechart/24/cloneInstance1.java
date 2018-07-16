        for (int i = 0; i < row; i++) {
            if (group.equals(this.seriesToGroupMap.getGroup(
                    dataset.getRowKey(i)))) {
                Number v = dataset.getValue(i, column);
                if (v != null) {
                    double d = v.doubleValue();
                    if (d > 0) {
                        positiveBase = positiveBase + d;
                    }
                    else {
                        negativeBase = negativeBase + d;
                    }
                }
            }
        }

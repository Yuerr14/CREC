        if (dataset.getDomainOrder() == DomainOrder.ASCENDING) {
            int low = 0;
            int high = itemCount - 1;
            double lowValue = dataset.getXValue(series, low);
            if (lowValue > x) {
                return new int[] {-1, -1};
            }
            if (lowValue == x) {
                return new int[] {low, low};
            }
            double highValue = dataset.getXValue(series, high);
            if (highValue < x) {
                return new int[] {-1, -1};
            }
            if (highValue == x) {
                return new int[] {high, high};
            }
            int mid = (low + high) / 2;
            while (high - low > 1) {
                double midV = dataset.getXValue(series, mid);
                if (x == midV) {
                    return new int[] {mid, mid};
                }
                if (midV < x) {
                    low = mid;
                }
                else {
                    high = mid;
                }
                mid = (low + high) / 2;
            }
            return new int[] {low, high};
        }

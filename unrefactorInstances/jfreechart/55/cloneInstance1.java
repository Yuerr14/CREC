    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FlowArrangement)) {
            return false;
        }
        FlowArrangement that = (FlowArrangement) obj;
        if (this.horizontalAlignment != that.horizontalAlignment) {
            return false;
        }
        if (this.verticalAlignment != that.verticalAlignment) {
            return false;
        }
        if (this.horizontalGap != that.horizontalGap) {
            return false;
        }
        if (this.verticalGap != that.verticalGap) {
            return false;
        }
        return true;
    }

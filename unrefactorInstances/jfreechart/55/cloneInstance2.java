    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ColumnArrangement)) {
            return false;
        }
        ColumnArrangement that = (ColumnArrangement) obj;
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

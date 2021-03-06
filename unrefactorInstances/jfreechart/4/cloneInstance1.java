    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScatterRenderer)) {
            return false;
        }
        ScatterRenderer that = (ScatterRenderer) obj;
        if (!ObjectUtils.equal(this.seriesShapesFilled,
                that.seriesShapesFilled)) {
            return false;
        }
        if (this.baseShapesFilled != that.baseShapesFilled) {
            return false;
        }
        if (this.useFillPaint != that.useFillPaint) {
            return false;
        }
        if (this.drawOutlines != that.drawOutlines) {
            return false;
        }
        if (this.useOutlinePaint != that.useOutlinePaint) {
            return false;
        }
        if (this.useSeriesOffset != that.useSeriesOffset) {
            return false;
        }
        if (this.itemMargin != that.itemMargin) {
            return false;
        }
        return super.equals(obj);
    }

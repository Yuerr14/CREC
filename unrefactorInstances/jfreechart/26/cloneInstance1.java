    public Shape getWindow(Rectangle2D frame) {

        Rectangle2D innerFrame = DialPlot.rectangleByRadius(frame,
                this.innerRadius, this.innerRadius);
        Rectangle2D outerFrame = DialPlot.rectangleByRadius(frame,
                this.outerRadius, this.outerRadius);
        Arc2D inner = new Arc2D.Double(innerFrame, this.startAngle,
                this.extent, Arc2D.OPEN);
        Arc2D outer = new Arc2D.Double(outerFrame, this.startAngle
                + this.extent, -this.extent, Arc2D.OPEN);
        GeneralPath p = new GeneralPath();
        Point2D point1 = inner.getStartPoint();
        p.moveTo((float) point1.getX(), (float) point1.getY());
        p.append(inner, true);
        p.append(outer, true);
        p.closePath();
        return p;

    }

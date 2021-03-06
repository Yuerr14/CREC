            Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {

        AxisState ret = super.draw(g2, cursor, plotArea, dataArea, edge, 
                plotState);
        if (isAdvanceLineVisible()) {
            double xx = valueToJava2D(getRange().getUpperBound(), dataArea, 
                    edge);
            Line2D mark = null;
            g2.setStroke(getAdvanceLineStroke());
            g2.setPaint(getAdvanceLinePaint());
            if (edge == RectangleEdge.LEFT) {
                mark = new Line2D.Double(cursor, xx, cursor 
                        + dataArea.getWidth(), xx);
            }
            else if (edge == RectangleEdge.RIGHT) {
                mark = new Line2D.Double(cursor - dataArea.getWidth(), xx, 
                        cursor, xx);
            }
            else if (edge == RectangleEdge.TOP) {
                mark = new Line2D.Double(xx, cursor + dataArea.getHeight(), xx, 
                        cursor);
            }
            else if (edge == RectangleEdge.BOTTOM) {
                mark = new Line2D.Double(xx, cursor, xx, 
                        cursor - dataArea.getHeight());
            }
            g2.draw(mark);
        }
        return ret;
    }

                if (range.contains(value)) {
                    double x = midX + getColumnRadius() + 2;
                    double y = this.rangeAxis.valueToJava2D(value, dataArea,
                            RectangleEdge.LEFT);
                    Line2D line = new Line2D.Double(x, y, x + 10, y);
                    g2.setPaint(this.subrangePaint[NORMAL]);
                    g2.draw(line);
                }

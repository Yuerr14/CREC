                                                   TickUnit unit) {

        RectangleInsets tickLabelInsets = getTickLabelInsets();
        double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();

        if (isVerticalTickLabels()) {
            // all tick labels have the same width (equal to the height of the
            // font)...
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = getTickLabelFont().getLineMetrics("0", frc);
            result += lm.getHeight();
        }
        else {
            // look at lower and upper bounds...
            FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
            Range range = getRange();
            double lower = range.getLowerBound();
            double upper = range.getUpperBound();
            String lowerStr, upperStr;
            NumberFormat formatter = getNumberFormatOverride();
            if (formatter != null) {
                lowerStr = formatter.format(lower);
                upperStr = formatter.format(upper);
            }
            else {
                lowerStr = unit.valueToString(lower);
                upperStr = unit.valueToString(upper);
            }
            double w1 = fm.stringWidth(lowerStr);
            double w2 = fm.stringWidth(upperStr);
            result += Math.max(w1, w2);
        }

        return result;

    }

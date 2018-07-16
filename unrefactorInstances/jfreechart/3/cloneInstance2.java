            String text, TextAnchor anchor, Rectangle2D textBounds) {

        float[] result = new float[3];
        FontRenderContext frc = g2.getFontRenderContext();
        Font f = g2.getFont();
        FontMetrics fm = g2.getFontMetrics(f);
        Rectangle2D bounds = getTextBounds(text, fm);
        LineMetrics metrics = f.getLineMetrics(text, frc);
        float ascent = metrics.getAscent();
        result[2] = -ascent;
        float halfAscent = ascent / 2.0f;
        float descent = metrics.getDescent();
        float leading = metrics.getLeading();
        float xAdj = 0.0f;
        float yAdj = 0.0f;

        if (anchor.isHorizontalCenter()) {
            xAdj = (float) -bounds.getWidth() / 2.0f;
        }
        else if (anchor.isRight()) {
            xAdj = (float) -bounds.getWidth();
        }

        if (anchor.isTop()) {
            yAdj = -descent - leading + (float) bounds.getHeight();
        }
        else if (anchor.isHalfAscent()) {
            yAdj = halfAscent;
        }
        else if (anchor.isHorizontalCenter()) {
            yAdj = -descent - leading + (float) (bounds.getHeight() / 2.0);
        }
        else if (anchor.isBaseline()) {
            yAdj = 0.0f;
        }
        else if (anchor.isBottom()) {
            yAdj = -metrics.getDescent() - metrics.getLeading();
        }
        if (textBounds != null) {
            textBounds.setRect(bounds);
        }
        result[0] = xAdj;
        result[1] = yAdj;
        return result;
    }

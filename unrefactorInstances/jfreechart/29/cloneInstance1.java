        if (vertical) {
            FontMetrics fm = g2.getFontMetrics(font);
            Iterator iterator = ticks.iterator();
            while (iterator.hasNext()) {
                Tick tick = (Tick) iterator.next();
                Rectangle2D labelBounds = null;
                if (tick instanceof LogTick) {
                    LogTick lt = (LogTick) tick;
                    if (lt.getAttributedLabel() != null) {
                        labelBounds = AttrStringUtils.getTextBounds(
                                lt.getAttributedLabel(), g2);
                    }
                } else if (tick.getText() != null) {
                    labelBounds = TextUtilities.getTextBounds(
                            tick.getText(), g2, fm);
                }
                if (labelBounds != null && labelBounds.getWidth() 
                        + insets.getTop() + insets.getBottom() > maxHeight) {
                    maxHeight = labelBounds.getWidth()
                                + insets.getTop() + insets.getBottom();
                }
            }
        } else {

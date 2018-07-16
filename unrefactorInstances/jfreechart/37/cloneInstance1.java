        if (this.labelLinksVisible) {
            double theta = record.getAngle();
            double linkX = state.getPieCenterX() + Math.cos(theta) 
                    * state.getPieWRadius() * record.getLinkPercent();
            double linkY = state.getPieCenterY() - Math.sin(theta) 
                    * state.getPieHRadius() * record.getLinkPercent();
            double elbowX = state.getPieCenterX() + Math.cos(theta) 
                    * state.getLinkArea().getWidth() / 2.0;
            double elbowY = state.getPieCenterY() - Math.sin(theta) 
                    * state.getLinkArea().getHeight() / 2.0;
            double anchorY = elbowY;
            g2.setPaint(this.labelLinkPaint);
            g2.setStroke(this.labelLinkStroke);
            g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
            g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
            g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
        }

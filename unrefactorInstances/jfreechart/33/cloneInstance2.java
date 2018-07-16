        if (this.shadowGenerator != null) {
            BufferedImage shadowImage
                    = this.shadowGenerator.createDropShadow(dataImage);
            g2 = savedG2;
            g2.drawImage(shadowImage, (int) dataArea.getX() 
                    + this.shadowGenerator.calculateOffsetX(),
                    (int) dataArea.getY() 
                    + this.shadowGenerator.calculateOffsetY(), null);
            g2.drawImage(dataImage, (int) dataArea.getX(),
                    (int) dataArea.getY(), null);
        }

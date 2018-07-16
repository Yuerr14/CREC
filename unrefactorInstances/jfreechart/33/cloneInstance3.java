            if (this.shadowGenerator != null) {
                BufferedImage shadowImage = this.shadowGenerator.createDropShadow(dataImage);
                g2 = savedG2;
                g2.drawImage(shadowImage, (int) area.getX() 
                        + this.shadowGenerator.calculateOffsetX(), 
                        (int) area.getY()
                        + this.shadowGenerator.calculateOffsetY(), null);
                g2.drawImage(dataImage, (int) area.getX(), (int) area.getY(), 
                        null);
            }

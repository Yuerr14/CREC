    public static void storeImage(String mimeType, Image image, OutputStream os) throws Exception {
        ImageWriter imageWriter = null;
        BufferedImage bufferedImage = (BufferedImage) image;
        
        Iterator iterator = javax.imageio.ImageIO.getImageWritersByMIMEType(mimeType);
        if (iterator.hasNext()) {
        	imageWriter = (ImageWriter) iterator.next();
        }
        ImageOutputStream ios = javax.imageio.ImageIO.createImageOutputStream(os);
        imageWriter.setOutput(ios);

        imageWriter.write(new IIOImage(bufferedImage, null, null));
        ios.flush();
        imageWriter.dispose();
    }

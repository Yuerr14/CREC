    private static byte[] createBytesFromImage(Image image, String mimeType)  {
        try {
            ImageWriter imageWriter = null;
            BufferedImage bufferedImage = (BufferedImage) image;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Iterator iterator = javax.imageio.ImageIO.getImageWritersByMIMEType(mimeType);
            if (iterator.hasNext()) {
                imageWriter = (ImageWriter) iterator.next();
            }
            ImageOutputStream ios = javax.imageio.ImageIO.createImageOutputStream(baos);
            imageWriter.setOutput(ios);
            imageWriter.write(new IIOImage(bufferedImage, null, null));
            ios.flush();
            imageWriter.dispose();
            return baos.toByteArray();
        } catch (IOException e) {
            throw ExceptionFactory.makeWebServiceException(e);
        }

    }

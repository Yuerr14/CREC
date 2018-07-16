    public void testEquals() {

        CategoryPointerAnnotation a1 = new CategoryPointerAnnotation("Label",
                "Key 1", 20.0, Math.PI);
        CategoryPointerAnnotation a2 = new CategoryPointerAnnotation("Label",
                "Key 1", 20.0, Math.PI);
        assertTrue(a1.equals(a2));

        a1 = new CategoryPointerAnnotation("Label2", "Key 1", 20.0, Math.PI);
        assertFalse(a1.equals(a2));
        a2 = new CategoryPointerAnnotation("Label2", "Key 1", 20.0, Math.PI);
        assertTrue(a1.equals(a2));

        a1.setCategory("Key 2");
        assertFalse(a1.equals(a2));
        a2.setCategory("Key 2");
        assertTrue(a1.equals(a2));

        a1.setValue(22.0);
        assertFalse(a1.equals(a2));
        a2.setValue(22.0);
        assertTrue(a1.equals(a2));

        //private double angle;
        a1.setAngle(Math.PI / 4.0);
        assertFalse(a1.equals(a2));
        a2.setAngle(Math.PI / 4.0);
        assertTrue(a1.equals(a2));

        //private double tipRadius;
        a1.setTipRadius(20.0);
        assertFalse(a1.equals(a2));
        a2.setTipRadius(20.0);
        assertTrue(a1.equals(a2));

        //private double baseRadius;
        a1.setBaseRadius(5.0);
        assertFalse(a1.equals(a2));
        a2.setBaseRadius(5.0);
        assertTrue(a1.equals(a2));

        //private double arrowLength;
        a1.setArrowLength(33.0);
        assertFalse(a1.equals(a2));
        a2.setArrowLength(33.0);
        assertTrue(a1.equals(a2));

        //private double arrowWidth;
        a1.setArrowWidth(9.0);
        assertFalse(a1.equals(a2));
        a2.setArrowWidth(9.0);
        assertTrue(a1.equals(a2));

        //private Stroke arrowStroke;
        Stroke stroke = new BasicStroke(1.5f);
        a1.setArrowStroke(stroke);
        assertFalse(a1.equals(a2));
        a2.setArrowStroke(stroke);
        assertTrue(a1.equals(a2));

        //private Paint arrowPaint;
        a1.setArrowPaint(Color.blue);
        assertFalse(a1.equals(a2));
        a2.setArrowPaint(Color.blue);
        assertTrue(a1.equals(a2));

        //private double labelOffset;
        a1.setLabelOffset(10.0);
        assertFalse(a1.equals(a2));
        a2.setLabelOffset(10.0);
        assertTrue(a1.equals(a2));

    }

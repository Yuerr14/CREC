    public void testCloning() {
        WindItemRenderer r1 = new WindItemRenderer();
        WindItemRenderer r2 = null;
        try {
            r2 = (WindItemRenderer) r1.clone();
        }
        catch (CloneNotSupportedException e) {
            System.err.println("Failed to clone.");
        }
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));
    }

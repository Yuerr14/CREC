    public void testCloning() {
        DefaultDrawingSupplier r1 = new DefaultDrawingSupplier();
        DefaultDrawingSupplier r2 = null;
        try {
            r2 = (DefaultDrawingSupplier) r1.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.err.println("Failed to clone.");
        }
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));
    }

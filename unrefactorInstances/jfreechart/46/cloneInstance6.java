    public void testCloning() {
        StackedBarRenderer r1 = new StackedBarRenderer();
        StackedBarRenderer r2 = null;
        try {
            r2 = (StackedBarRenderer) r1.clone();
        }
        catch (CloneNotSupportedException e) {
            System.err.println("Failed to clone.");
        }
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));
    }

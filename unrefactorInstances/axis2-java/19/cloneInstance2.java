    public void testTestClass102() {

        Class testClass = TestClass10.class;
        Parameter parameter = new Parameter(testClass, "Param1");
        TestClass10 testObject = new TestClass10();
        testObject.setParam1(new Integer(5));
        TestClass10 result = (TestClass10) getReturnObject(parameter, testObject);
        assertEquals(result.getParam1(), new Integer(5));
    }

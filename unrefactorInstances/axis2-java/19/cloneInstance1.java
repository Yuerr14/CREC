    public void testTestClass101() {

        Class testClass = TestClass10.class;
        Parameter parameter = new Parameter(testClass, "Param1");
        TestClass10 testObject = new TestClass10();
        testObject.setParam1("Test String");
        TestClass10 result = (TestClass10) getReturnObject(parameter, testObject);
        assertEquals(result.getParam1(), "Test String");
    }

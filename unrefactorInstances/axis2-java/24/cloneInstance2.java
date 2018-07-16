    public void testHolderGeneric() {
        String holderInputString = JAXWS_HOLDER + "<java.util.List<java.lang.Object>>";
        assertTrue(ParameterDescriptionComposite.isHolderType(holderInputString));
        String holderResultString = ParameterDescriptionComposite.getRawType(holderInputString);
        assertEquals(JAXWS_HOLDER, holderResultString);
        
        String actualTypeResult = ParameterDescriptionComposite.getHolderActualType(holderInputString);
        assertEquals("java.util.List", actualTypeResult);
        
    }

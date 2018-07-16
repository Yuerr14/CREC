    public void testSetExtensionFactory() {
        QName name = new QName(TEST_LOCAL_NAME);
        try {
            this.impl.getChildParser(name);
            fail("no child hander for this qname");
        } catch (GDataParseException e) {
            // 
        }

        this.impl.setExtensionFactory(new TestExtendsionFactory());

        AtomParser childParser = this.impl.getChildParser(name);
        assertTrue(childParser instanceof TestExtension);

    }

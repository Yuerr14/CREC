    public void testProcessEndElement() {
        this.impl.addAuthor(new GOMAuthorImpl());
        this.impl.setId(new GOMIdImpl());
        this.impl.setUpdated(new GOMUpdatedImpl());
        this.impl.setTitle(new GOMTitleImpl());

        this.impl.processEndElement();
        {
            // author missing
            this.impl.getAuthors().clear();
            try {
                this.impl.processEndElement();
                fail("missing elements");
            } catch (GDataParseException e) {
                // 
            }
            this.impl.addAuthor(new GOMAuthorImpl());
        }

        {
            // id missing
            this.impl.setId(null);
            try {
                this.impl.processEndElement();
                fail("missing elements");
            } catch (GDataParseException e) {
                // 
            }
            this.impl.setId(new GOMIdImpl());
        }

        {
            // title missing
            this.impl.setTitle(null);
            try {
                this.impl.processEndElement();
                fail("missing elements");
            } catch (GDataParseException e) {
                // 
            }
            this.impl.setTitle(new GOMTitleImpl());
        }
        {
            // updated missing
            this.impl.setUpdated(null);
            try {
                this.impl.processEndElement();
                fail("missing elements");
            } catch (GDataParseException e) {
                // 
            }
            this.impl.setUpdated(new GOMUpdatedImpl());
        }

        /*
         * atom:feed elements MUST NOT contain more than one atom:link element
         * with a rel attribute value of "alternate" that has the same
         * combination of type and hreflang attribute values.
         */

        {
            // two identical alternate links missing
            GOMLink link = new GOMLinkImpl();
            link.setRel("alternate");
            link.setHrefLang("http://www.apache.org");
            link.setType("text/html");
            this.impl.addLink(link);
            // one is allowed
            this.impl.processEndElement();
            // add a second link
            link = new GOMLinkImpl();
            this.impl.addLink(link);
            link.setRel("next");
            link.setHrefLang("http://www.apache.org");
            link.setType("text/html");
            // one is alternate the other is next
            this.impl.processEndElement();

            // a second "identical" alternate link
            link = new GOMLinkImpl();
            this.impl.addLink(link);
            link.setRel("alternate");
            link.setHrefLang("http://www.apache.org");
            link.setType("text/html");
            try {
                this.impl.processEndElement();
                fail("missing elements");
            } catch (GDataParseException e) {
                // 
            }
            this.impl.setUpdated(new GOMUpdatedImpl());
        }

    }

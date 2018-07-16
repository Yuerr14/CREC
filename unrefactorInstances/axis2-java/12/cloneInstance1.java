            while (iterator.hasNext()) {
                Element enumFacet = XSLTUtils.addChildElement(model, "enumFacet", property);
                String attribValue = (String) iterator.next();
                XSLTUtils.addAttribute(model, "value", attribValue, enumFacet);
                if (validJava) {
                    XSLTUtils.addAttribute(model, "id", attribValue, enumFacet);
                } else {
                    id++;
                    XSLTUtils.addAttribute(model, "id", "value" + id, enumFacet);
                }
            }

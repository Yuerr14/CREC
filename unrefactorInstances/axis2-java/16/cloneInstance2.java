        } else {
            // no addressing headers present
            if(!isAddressingOptional){
                throw new AxisFault("Addressing Handlers should present, but doesn't present in the incoming message !!");
            }
            logger.debug("No Addressing Headers present in the IN message. Addressing In Handler does nothing.");
        }

        if (language != null) {

            if (country != null) {

                if (variant != null) {
                    props = merge(props, loadProperties(basename + "_" + language +"_" + country + "_" + variant +
                                                        PROPERTY_EXT, loader));
                }
                props = merge(props, loadProperties(basename + "_" + language +"_" + country +
                                                    PROPERTY_EXT, loader));
            }
            props = merge(props, loadProperties(basename + "_" + language + PROPERTY_EXT, loader));
        }

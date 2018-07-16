            try {
                KeyStore trustStore = KeyStore.getInstance(type);
                URL url = getClass().getClassLoader().getResource(location);
                log.debug("Loading Trust Key Store from URL : " + url);

                trustStore.load(url.openStream(), storePassword.toCharArray());
                TrustManagerFactory trustManagerfactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
                trustManagerfactory.init(trustStore);
                trustManagers = trustManagerfactory.getTrustManagers();

            } catch (GeneralSecurityException gse) {

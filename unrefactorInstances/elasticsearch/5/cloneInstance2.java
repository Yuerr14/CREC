    public RestMultiTermVectorsAction(Settings settings, RestController controller, Client client) {
        super(settings, controller, client);
        controller.registerHandler(GET, "/_mtermvectors", this);
        controller.registerHandler(POST, "/_mtermvectors", this);
        controller.registerHandler(GET, "/{index}/_mtermvectors", this);
        controller.registerHandler(POST, "/{index}/_mtermvectors", this);
        controller.registerHandler(GET, "/{index}/{type}/_mtermvectors", this);
        controller.registerHandler(POST, "/{index}/{type}/_mtermvectors", this);
    }

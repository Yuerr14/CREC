    public void testIndexLifecycleActionsWith11Shards1Backup() throws Exception {
        Settings settings = settingsBuilder()
                .put(SETTING_NUMBER_OF_SHARDS, 11)
                .put(SETTING_NUMBER_OF_REPLICAS, 1)
                .put("cluster.routing.schedule", "20ms") // reroute every 20ms so we identify new nodes fast
                .build();

        // start one server
        logger.info("Starting sever1");
        startNode("server1", settings);

        ClusterService clusterService1 = ((InternalNode) node("server1")).injector().getInstance(ClusterService.class);
        String node1 = clusterService1.state().nodes().localNodeId();

        wipeIndices(client());

        logger.info("Creating index [test]");
        CreateIndexResponse createIndexResponse = client("server1").admin().indices().create(createIndexRequest("test")).actionGet();
        assertThat(createIndexResponse.isAcknowledged(), equalTo(true));

        logger.info("Running Cluster Health");
        ClusterHealthResponse clusterHealth = client("server1").admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setWaitForYellowStatus().execute().actionGet();
        logger.info("Done Cluster Health, status " + clusterHealth.getStatus());
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.YELLOW));

        ClusterState clusterState = client().admin().cluster().prepareState().get().getState();
        RoutingNode routingNodeEntry1 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node1);
        assertThat(routingNodeEntry1.numberOfShardsWithState(STARTED), equalTo(11));

        logger.info("Starting server2");
        // start another server
        startNode("server2", settings);

        // first wait for 2 nodes in the cluster
        logger.info("Running Cluster Health");
        clusterHealth = client("server1").admin().cluster().health(clusterHealthRequest().waitForGreenStatus().waitForNodes("2")).actionGet();
        logger.info("Done Cluster Health, status " + clusterHealth.getStatus());
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.GREEN));

        ClusterService clusterService2 = ((InternalNode) node("server2")).injector().getInstance(ClusterService.class);
        String node2 = clusterService2.state().nodes().localNodeId();

        // explicitly call reroute, so shards will get relocated to the new node (we delay it in ES in case other nodes join)
        client("server1").admin().cluster().prepareReroute().execute().actionGet();

        clusterHealth = client("server1").admin().cluster().health(clusterHealthRequest().waitForGreenStatus().waitForNodes("2").waitForRelocatingShards(0)).actionGet();
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.GREEN));
        assertThat(clusterHealth.getNumberOfDataNodes(), equalTo(2));
        assertThat(clusterHealth.getInitializingShards(), equalTo(0));
        assertThat(clusterHealth.getUnassignedShards(), equalTo(0));
        assertThat(clusterHealth.getRelocatingShards(), equalTo(0));
        assertThat(clusterHealth.getActiveShards(), equalTo(22));
        assertThat(clusterHealth.getActivePrimaryShards(), equalTo(11));


        clusterState = client().admin().cluster().prepareState().get().getState();
        assertNodesPresent(clusterState.readOnlyRoutingNodes().nodesToShards(), node1, node2);
        routingNodeEntry1 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node1);
        assertThat(routingNodeEntry1.numberOfShardsWithState(RELOCATING), equalTo(0));
        assertThat(routingNodeEntry1.numberOfShardsWithState(STARTED), equalTo(11));
        RoutingNode routingNodeEntry2 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node2);
        assertThat(routingNodeEntry2.numberOfShardsWithState(INITIALIZING), equalTo(0));
        assertThat(routingNodeEntry2.numberOfShardsWithState(STARTED), equalTo(11));

        logger.info("Starting server3");
        // start another server
        startNode("server3", settings);

        // first wait for 3 nodes in the cluster
        clusterHealth = client("server1").admin().cluster().health(clusterHealthRequest().waitForGreenStatus().waitForNodes("3")).actionGet();
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.GREEN));


        ClusterService clusterService3 = ((InternalNode) node("server3")).injector().getInstance(ClusterService.class);
        String node3 = clusterService3.state().nodes().localNodeId();

        // explicitly call reroute, so shards will get relocated to the new node (we delay it in ES in case other nodes join)
        client("server1").admin().cluster().prepareReroute().execute().actionGet();

        clusterHealth = client("server1").admin().cluster().health(clusterHealthRequest().waitForGreenStatus().waitForNodes("3").waitForRelocatingShards(0)).actionGet();
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.GREEN));
        assertThat(clusterHealth.getNumberOfDataNodes(), equalTo(3));
        assertThat(clusterHealth.getInitializingShards(), equalTo(0));
        assertThat(clusterHealth.getUnassignedShards(), equalTo(0));
        assertThat(clusterHealth.getRelocatingShards(), equalTo(0));
        assertThat(clusterHealth.getActiveShards(), equalTo(22));
        assertThat(clusterHealth.getActivePrimaryShards(), equalTo(11));


        clusterState = client().admin().cluster().prepareState().get().getState();
        assertNodesPresent(clusterState.readOnlyRoutingNodes().nodesToShards(), node1, node2, node3);

        routingNodeEntry1 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node1);
        routingNodeEntry2 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node2);
        RoutingNode routingNodeEntry3 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node3);

        assertThat(routingNodeEntry1.numberOfShardsWithState(STARTED) + routingNodeEntry2.numberOfShardsWithState(STARTED) + routingNodeEntry3.numberOfShardsWithState(STARTED), equalTo(22));

        assertThat(routingNodeEntry1.numberOfShardsWithState(RELOCATING), equalTo(0));
        assertThat(routingNodeEntry1.numberOfShardsWithState(STARTED), anyOf(equalTo(7), equalTo(8)));

        assertThat(routingNodeEntry2.numberOfShardsWithState(RELOCATING), equalTo(0));
        assertThat(routingNodeEntry2.numberOfShardsWithState(STARTED), anyOf(equalTo(7), equalTo(8)));

        assertThat(routingNodeEntry3.numberOfShardsWithState(INITIALIZING), equalTo(0));
        assertThat(routingNodeEntry3.numberOfShardsWithState(STARTED), equalTo(7));


        logger.info("Closing server1");
        // kill the first server
        closeNode("server1");
        // verify health
        logger.info("Running Cluster Health");
        clusterHealth = client("server2").admin().cluster().health(clusterHealthRequest().waitForGreenStatus().waitForNodes("2")).actionGet();
        logger.info("Done Cluster Health, status " + clusterHealth.getStatus());
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.GREEN));

        client().admin().cluster().prepareReroute().get();

        clusterHealth = client("server2").admin().cluster().health(clusterHealthRequest().waitForGreenStatus().waitForRelocatingShards(0).waitForNodes("2")).actionGet();
        assertThat(clusterHealth.isTimedOut(), equalTo(false));
        assertThat(clusterHealth.getStatus(), equalTo(ClusterHealthStatus.GREEN));
        assertThat(clusterHealth.getRelocatingShards(), equalTo(0));
        assertThat(clusterHealth.getActiveShards(), equalTo(22));
        assertThat(clusterHealth.getActivePrimaryShards(), equalTo(11));

        clusterState = client().admin().cluster().prepareState().get().getState();
        assertNodesPresent(clusterState.readOnlyRoutingNodes().nodesToShards(), node3, node2);
        routingNodeEntry2 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node2);
        routingNodeEntry3 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node3);

        assertThat(routingNodeEntry2.numberOfShardsWithState(STARTED) + routingNodeEntry3.numberOfShardsWithState(STARTED), equalTo(22));

        assertThat(routingNodeEntry2.numberOfShardsWithState(RELOCATING), equalTo(0));
        assertThat(routingNodeEntry2.numberOfShardsWithState(STARTED), equalTo(11));

        assertThat(routingNodeEntry3.numberOfShardsWithState(RELOCATING), equalTo(0));
        assertThat(routingNodeEntry3.numberOfShardsWithState(STARTED), equalTo(11));


        logger.info("Deleting index [test]");
        // last, lets delete the index
        DeleteIndexResponse deleteIndexResponse = client("server2").admin().indices().prepareDelete("test").execute().actionGet();
        assertThat(deleteIndexResponse.isAcknowledged(), equalTo(true));

        clusterState = client().admin().cluster().prepareState().get().getState();
        assertNodesPresent(clusterState.readOnlyRoutingNodes().nodesToShards(), node3, node2);
        routingNodeEntry2 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node2);
        assertThat(routingNodeEntry2.shards().isEmpty(), equalTo(true));

        routingNodeEntry3 = clusterState.readOnlyRoutingNodes().nodesToShards().get(node3);
        assertThat(routingNodeEntry3.shards().isEmpty(), equalTo(true));
    }

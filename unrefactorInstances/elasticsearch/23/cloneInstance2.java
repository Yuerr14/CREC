            public void run() {
                ClusterState clusterState = internalCluster().clusterService().state();
                ShardRouting shardRouting = clusterState.routingTable().index("test").shard(0).getShards().get(0);
                String nodeName = clusterState.getNodes().get(shardRouting.currentNodeId()).getName();

                boolean verified = false;
                IndicesService indicesService = internalCluster().getInstance(IndicesService.class, nodeName);
                IndexService indexService = indicesService.indexService("test");
                if (indexService != null) {
                    MapperService mapperService = indexService.mapperService();
                    DocumentMapper documentMapper = mapperService.documentMapper("child");
                    if (documentMapper != null) {
                        verified = documentMapper.parentFieldMapper().fieldType().fieldDataType().getLoading() == MappedFieldType.Loading.EAGER_GLOBAL_ORDINALS;
                    }
                }
                assertTrue(verified);
            }

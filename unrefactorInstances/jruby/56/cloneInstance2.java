                                   Object value, RubyMethod method, boolean indirect) {
        super(context, cachedName, next, indirect);

        this.expectedClass = expectedClass;
        this.unmodifiedAssumption = unmodifiedAssumption;
        this.next = next;
        this.value = value;
        this.method = method;

        if (method != null) {
            if (indirect) {
                indirectCallNode = Truffle.getRuntime().createIndirectCallNode();
            } else {
                callNode = Truffle.getRuntime().createDirectCallNode(method.getCallTarget());

                if (callNode.isSplittable() && method.getSharedMethodInfo().shouldAlwaysSplit()) {
                    insert(callNode);
                    callNode.split();
                }
            }
        }
    }

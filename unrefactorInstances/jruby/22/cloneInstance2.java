    public boolean hasNext() {
        // Multiple hasNext calls with no next...hasNext still true
        if (nextEdge != null) return true;

        while (internalIterator.hasNext()) {
            Edge edge = internalIterator.next();
            Object edgeType = edge.getType();

            if (negate) {
                // When edgeType or type is null compare them directly. Otherwise compare them using equals
                if ((edgeType != null && !edgeType.equals(type)) || (edgeType == null && edgeType != type)) {
                    nextEdge = edge;
                    return true;
                }
                // When edgeType or type is null compare them directly. Otherwise compare them using equals
            } else if ((edgeType != null && edgeType.equals(type)) || (edgeType == null && edgeType == type)) {
                nextEdge = edge;
                return true;
            }
        }

        return false;
    }

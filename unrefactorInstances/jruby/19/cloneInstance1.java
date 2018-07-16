    public ListNode add(Node node) {
        // Ruby Grammar productions return plenty of nulls.
        if (node == null) return this;
        if (list == null) {
            list = new Node[1];
        } else {
            Node[] newList = new Node[list.length + 1];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;
        }

        list[list.length - 1] = node;

        if (getPosition() == null) {
            setPosition(node.getPosition());
        } else {
            setPosition(getPosition().union(node.getPosition()));
        }

        return this;
    }

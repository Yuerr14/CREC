    public String toString() {
        String sSource = "_na_";
        try {
            sSource = XContentHelper.convertToJson(source, false);
        } catch (Exception e) {
            // ignore
        }
        return "delete-by-query [" + Arrays.toString(indices) + "][" + Arrays.toString(types) + "], source[" + sSource + "]";
    }

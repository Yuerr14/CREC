    public FieldSelectorResult accept(String fieldName) {
        if (UidFieldMapper.NAME.equals(fieldName)) {
            if (++match == 2) {
                return FieldSelectorResult.LOAD_AND_BREAK;
            }
            return FieldSelectorResult.LOAD;
        }
        if (RoutingFieldMapper.NAME.equals(fieldName)) {
            if (++match == 2) {
                return FieldSelectorResult.LOAD_AND_BREAK;
            }
            return FieldSelectorResult.LOAD;
        }
        return FieldSelectorResult.NO_LOAD;
    }

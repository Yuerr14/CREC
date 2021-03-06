    public String toString() {
        Iterator<Entry<TypeK, TypeV>> i = entrySet().iterator();
        if (!i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (; ;) {
            Entry<TypeK, TypeV> e = i.next();
            TypeK key = e.getKey();
            TypeV value = e.getValue();
            sb.append(key == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (!i.hasNext())
                return sb.append('}').toString();
            sb.append(", ");
        }
    }

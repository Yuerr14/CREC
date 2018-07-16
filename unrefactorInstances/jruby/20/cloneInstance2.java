    public IRubyObject start_mapping(ThreadContext context, IRubyObject[] args) {
        IRubyObject anchor = args[0];
        IRubyObject tag = args[1];
        IRubyObject implicit = args[2];
        IRubyObject style = args[3];

        final int MAPPING_BLOCK = 1; // see psych/nodes/mapping.rb

        MappingStartEvent event = new MappingStartEvent(
                anchor.isNil() ? null : anchor.asJavaString(),
                tag.isNil() ? null : tag.asJavaString(),
                implicit.isTrue(),
                NULL_MARK,
                NULL_MARK,
                MAPPING_BLOCK != style.convertToInteger().getLongValue());
        emit(context, event);
        return this;
    }

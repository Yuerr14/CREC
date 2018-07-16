    public IRubyObject start_sequence(ThreadContext context, IRubyObject[] args) {
        IRubyObject anchor = args[0];
        IRubyObject tag = args[1];
        IRubyObject implicit = args[2];
        IRubyObject style = args[3];

        final int SEQUENCE_BLOCK = 1; // see psych/nodes/sequence.rb

        SequenceStartEvent event = new SequenceStartEvent(
                anchor.isNil() ? null : anchor.asJavaString(),
                tag.isNil() ? null : tag.asJavaString(),
                implicit.isTrue(),
                NULL_MARK,
                NULL_MARK,
                SEQUENCE_BLOCK != style.convertToInteger().getLongValue());
        emit(context, event);
        return this;
    }

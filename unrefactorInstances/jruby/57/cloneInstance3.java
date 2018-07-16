    public Object dispatch(VirtualFrame frame, NilPlaceholder methodReceiverObject, Object boxedCallingSelf, RubyBasicObject receiverObject, Object blockObject, Object argumentsObjects) {
        // Check the lookup node is what we expect

        if (receiverObject.getLookupNode() != expectedLookupNode) {
            return doNext(frame, methodReceiverObject, boxedCallingSelf, receiverObject, CompilerDirectives.unsafeCast(blockObject, RubyProc.class, true, false), argumentsObjects);
        }
        return doDispatch(frame, methodReceiverObject, boxedCallingSelf, receiverObject, CompilerDirectives.unsafeCast(blockObject, RubyProc.class, true, false), CompilerDirectives.unsafeCast(argumentsObjects, Object[].class, true, true));

    }

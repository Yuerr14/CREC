    public CallTarget getAccess(Message tree) {
    	if (Read.create(Receiver.create(), Argument.create()).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createRead(context, new NullSourceSection("", ""), (Read) tree)));
    	} else if (Execute.create(Read.create(Receiver.create(), Argument.create()),0).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createExecuteAfterRead(context, new NullSourceSection("", ""), (Execute) tree)));
    	} else if (Write.create(Receiver.create(), Argument.create(), Argument.create()).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createWrite(context, new NullSourceSection("", ""), (Write) tree)));
        } else if (IsExecutable.create(Receiver.create()).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createIsExecutable(context, new NullSourceSection("", ""))));
        } else if (IsBoxed.create(Receiver.create()).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createIsBoxedPrimitive(context, new NullSourceSection("", ""))));
        } else if (IsNull.create(Receiver.create()).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createIsNull(context, new NullSourceSection("", ""))));
        } else if (HasSize.create(Receiver.create()).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createHasSizePropertyTrue(context, new NullSourceSection("", ""))));
        } else if (GetSize.create(Receiver.create()).matchStructure(tree)) {
            return Truffle.getRuntime().createCallTarget(new RubyInteropRootNode(InteropNode.createGetSize(context, new NullSourceSection("", ""))));
        } else {
            throw new UnsupportedMessageException("Message not supported: " + tree.toString());
        }
    }

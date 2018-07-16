	private IOModes getModes(IRubyObject object) {
		if (object instanceof RubyString) {
			return new IOModes(getRuntime(), ((RubyString)object).getValue());
		} else if (object instanceof RubyFixnum) {
			return new IOModes(getRuntime(), ((RubyFixnum)object).getLongValue());
		}

		throw getRuntime().newTypeError("Invalid type for modes");
	}

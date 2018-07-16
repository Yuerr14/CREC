    public IRubyObject shutdown(IRubyObject[] args) {
        if (getRuntime().getSafeLevel() >= 4 && tainted().isFalse()) {
            throw getRuntime().newSecurityError("Insecure: can't shutdown socket");
        }
        
        int how = 2;
        if (args.length > 0) {
            how = RubyNumeric.fix2int(args[0]);
        }
        if (how < 0 || 2 < how) {
            throw getRuntime().newArgumentError("`how' should be either 0, 1, 2");
        }
        if (how != 2) {
            throw getRuntime().newNotImplementedError("Shutdown currently only works with how=2");
        }
        return close();
    }

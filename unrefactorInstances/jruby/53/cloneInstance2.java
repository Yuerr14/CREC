    public static IRubyObject isJavaExceptionHandled(Throwable currentThrowable, IRubyObject throwable0, IRubyObject throwable1, IRubyObject throwable2, ThreadContext context) {
        if (currentThrowable instanceof Unrescuable) {
            UnsafeFactory.getUnsafe().throwException(currentThrowable);
        }
        
        if (currentThrowable instanceof RaiseException) {
            return isExceptionHandled(((RaiseException)currentThrowable).getException(), throwable0, throwable1, throwable2, context);
        } else {
            if (checkJavaException(currentThrowable, throwable0, context)) {
                return context.getRuntime().getTrue();
            }
            if (checkJavaException(currentThrowable, throwable1, context)) {
                return context.getRuntime().getTrue();
            }
            if (checkJavaException(currentThrowable, throwable2, context)) {
                return context.getRuntime().getTrue();
            }

            return context.getRuntime().getFalse();
        }
    }

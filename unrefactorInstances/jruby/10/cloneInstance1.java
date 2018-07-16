        } else {
            if (arg instanceof Float) {
                return new UnboxedFloat(((Float)arg).getValue());
            } else if (arg instanceof Fixnum) {
                return new UnboxedFixnum(((Fixnum)arg).getValue());
            } else if (arg instanceof org.jruby.ir.operands.Boolean) {
                return new UnboxedBoolean(((Boolean)arg).isTrue());
            }
            // This has to be a known operand like (UnboxedBoolean, etc.)
            return arg;
        }

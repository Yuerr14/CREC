        } else {
            if (arg instanceof Float) {
                return new UnboxedFloat(((Float)arg).getValue());
            } else if (arg instanceof Fixnum) {
                return new UnboxedFixnum(((Fixnum)arg).getValue());
            } else if (arg instanceof Boolean) {
                return new UnboxedBoolean(((Boolean)arg).isTrue());
            }
            return arg;
        }

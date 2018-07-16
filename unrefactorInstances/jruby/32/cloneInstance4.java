    public void execute(RubyBasicObject object, Object value) {
        try {
            expectedLayout.getValidAssumption().check();
            newLayout.getValidAssumption().check();
        } catch (InvalidAssumptionException e) {
            replace(next);
            next.execute(object, value);
            return;
        }

        if (object.getObjectLayout() == expectedLayout && storageLocation.canStore(value)) {
            try {
                if (newLayout == expectedLayout) {
                    storageLocation.set(object.getDynamicObject(), value, expectedLayout);
                } else {
                    storageLocation.set(object.getDynamicObject(), value, expectedLayout, newLayout);
                }
            } catch (IncompatibleLocationException e) {
                throw new UnsupportedOperationException();
            } catch (FinalLocationException e) {
                replace(next, "!final").execute(object, value);
            }
        } else {
            next.execute(object, value);
        }
    }

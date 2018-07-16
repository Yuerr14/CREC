    public void execute(RubyBasicObject object, int value) {
        try {
            expectedLayout.getValidAssumption().check();
            newLayout.getValidAssumption().check();
        } catch (InvalidAssumptionException e) {
            replace(next);
            next.execute(object, value);
            return;
        }

        if (object.getObjectLayout() == expectedLayout) {
            try {
                if (newLayout == expectedLayout) {
                    storageLocation.setInt(object.getDynamicObject(), value, expectedLayout);
                } else {
                    storageLocation.setInt(object.getDynamicObject(), value, expectedLayout, newLayout);
                }
            } catch (FinalLocationException e) {
                replace(next, "!final").execute(object, value);
            }
        } else {
            next.execute(object, value);
        }
    }

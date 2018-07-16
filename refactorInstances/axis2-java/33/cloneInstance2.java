        if (action != null) {
            FaultAction[] faultActions = action.fault();
            
            if (faultActions != null) {
                for (FaultAction faultAction : faultActions) {
                    String className = faultAction.className().getName();
                    FaultDescription faultDesc = resolveFaultByExceptionName(className);
                    if (faultDesc != null)  {
                        newAxisOperation.addFaultAction(faultDesc.getName(), faultAction.value());
                    }
                }
            }
        }

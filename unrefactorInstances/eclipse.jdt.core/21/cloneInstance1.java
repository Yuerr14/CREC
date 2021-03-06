public void removeNotDefinitelyAssignedVariables(Scope scope, int initStateIndex) {
	// given some flow info, make sure we did not loose some variables initialization
	// if this happens, then we must update their pc entries to reflect it in debug attributes
	if ((this.generateAttributes & ClassFileConstants.ATTR_VARS) == 0)
		return;
/*	if (initStateIndex == lastInitStateIndexWhenRemovingInits)
		return;
		
	lastInitStateIndexWhenRemovingInits = initStateIndex;
	if (lastInitStateIndexWhenAddingInits != initStateIndex){
		lastInitStateIndexWhenAddingInits = -2;// reinitialize add index 
		// add(1)-remove(1)-add(1) -> ignore second add
		// add(1)-remove(2)-add(1) -> perform second add
	}*/
	for (int i = 0; i < visibleLocalsCount; i++) {
		LocalVariableBinding localBinding = visibleLocals[i];
		if (localBinding != null && !isDefinitelyAssigned(scope, initStateIndex, localBinding) && localBinding.initializationCount > 0) {
			localBinding.recordInitializationEndPC(position);
		}
	}
}

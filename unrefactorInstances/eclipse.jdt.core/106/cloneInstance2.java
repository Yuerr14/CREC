	    if(CompletionEngine.PROPOSE_MEMBER_TYPES) {
			assertResults(
				"CompletionSuperInterface[TYPE_REF]{CompletionSuperInterface, , LCompletionSuperInterface;, null, null, "+(R_DEFAULT + R_INTERESTING + R_CASE + R_UNQUALIFIED + R_INTERFACE + R_NON_RESTRICTED)+"}\n" +
				"CompletionSuperInterface2[TYPE_REF]{CompletionSuperInterface2, , LCompletionSuperInterface2;, null, null, "+(R_DEFAULT + R_INTERESTING + R_CASE + R_UNQUALIFIED + R_INTERFACE+ R_NON_RESTRICTED)+"}",
				requestor.getResults());
	    } else {

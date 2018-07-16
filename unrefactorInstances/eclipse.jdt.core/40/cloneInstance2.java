	public void record(IProblem newProblem, ReferenceContext referenceContext) {

		if (newProblem.getID() == IProblem.Task) {
			recordTask(newProblem);
			return;
		}
		if (problemCount == 0) {
			problems = new IProblem[5];
		} else if (problemCount == problems.length) {
			System.arraycopy(problems, 0, (problems = new IProblem[problemCount * 2]), 0, problemCount);
		}
		problems[problemCount++] = newProblem;
		if (referenceContext != null){
			if (problemsMap == null) problemsMap = new Hashtable(5);
			if (firstErrorsMap == null) firstErrorsMap = new Hashtable(5);
			if (newProblem.isError() && !referenceContext.hasErrors()) firstErrorsMap.put(newProblem, newProblem);
			problemsMap.put(newProblem, referenceContext);
		}
	}

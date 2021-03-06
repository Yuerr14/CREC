public void testBug124624_HaM_old() throws CoreException {
	workingCopies = new ICompilationUnit[1];
	workingCopies[0] = getWorkingCopy("/JavaSearchBugs/src/Test.java",
		"class HashMap {}\n" + 
		"class HtmlMapper {}\n" + 
		"class HashMapEntry {}\n" + 
		"class HatMappage {}\n"
	);
	search("HaM", IJavaSearchConstants.TYPE, IJavaSearchConstants.DECLARATIONS, SearchPattern.R_CAMELCASE_MATCH);
	assertSearchResults(
		"src/Test.java HashMap [HashMap] EXACT_MATCH\n" + 
		"src/Test.java HashMapEntry [HashMapEntry] EXACT_MATCH\n" + 
		"src/Test.java HatMappage [HatMappage] EXACT_MATCH"
	);
}

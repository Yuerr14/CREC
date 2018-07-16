public void testBug400919a() throws CoreException {
	this.workingCopies = new ICompilationUnit[1];
	this.workingCopies[0] = getWorkingCopy("/JavaSearchBugs/src/b400919/X.java",
			"import java.lang.annotation.ElementType;\n" +
			"import java.lang.annotation.Target;\n" +
			"import java.util.Collection;\n" +
			"\n" +
			"interface I {\n" +
			"	I doit();\n" +
			"}\n" +
			"\n" +
			"@Marker public class X {\n" +
			"   @SuppressWarnings(\"unused\")\n" +
			"	@Marker <@Existing T>  int x(@Existing T t) { return 10; };\n" +
			"	/**\n" +
			"	 * @param <F>  \n" +
			"	 */\n" +
			"	class Folder<@Existing  F extends @Existing XYZ> {  }\n" +
			"	Collection<@Existing ? super @Existing XYZ> s;\n" +
			"	/**\n" +
			"	 * @param <T>  \n" +
			"	 */\n" +
			"	class Test <T extends Outer.@Existing Inner> {}\n" +
			"}\n" +
			"\n" +
			"class Y extends  Object  {\n" +
			"	int x = ( int) 0;\n" +
			"}\n" +
			"\n" +
			"/**\n" +
			" * @param <T>  \n" +
			" */\n" +
			"class XY<@Existing T> {}\n" +
			"class XYZ {}\n" +
			"\n" +
			"class Outer {\n" +
			"	class Inner {\n" +
			"		\n" +
			"	}\n" +
			"}\n" +
			"/**\n" +
			" * @param <T> \n" +
			" * @param <Q>  \n" +
			" */\n" +
			"class X2 <@Marker T extends @Marker Y2<@Marker ? extends @Marker X>, @Marker Q extends @Marker Object> {\n" +
			"}\n" +
			"/**\n" +
			" * @param <T>  \n" +
			" */\n" +
			"class Y2<T> {}\n" +
			"@Target(ElementType.TYPE_USE)\n" +
			"@interface Existing {\n" +
			"	\n" +
			"}\n" +
			"@Target (ElementType.TYPE_USE)\n" +
			"@interface Marker {}\n"
		);
	SearchPattern pattern = SearchPattern.createPattern(
			"Existing",
			ANNOTATION_TYPE,
			REFERENCES,
			EXACT_RULE);
	new SearchEngine(this.workingCopies).search(pattern,
	new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()},
	getJavaSearchWorkingCopiesScope(),
	this.resultCollector,
	null);
	assertSearchResults(
			"src/b400919/X.java b400919.X.s [Existing] EXACT_MATCH\n" + 
			"src/b400919/X.java b400919.X.s [Existing] EXACT_MATCH\n" + 
			"src/b400919/X.java int b400919.X.x(T) [Existing] EXACT_MATCH\n" + 
			"src/b400919/X.java int b400919.X.x(T) [Existing] EXACT_MATCH\n" + 
			"src/b400919/X.java b400919.X$Folder [Existing] EXACT_MATCH\n" + 
			"src/b400919/X.java b400919.X$Folder [Existing] EXACT_MATCH\n" + 
			"src/b400919/X.java b400919.X$Test [Existing] EXACT_MATCH\n" + 
			"src/b400919/X.java b400919.XY [Existing] EXACT_MATCH"
	);	
}

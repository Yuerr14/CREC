 */
public void testResolveUnicode() throws JavaModelException {
	ICompilationUnit cu = getCompilationUnit("Resolve", "src", "", "ResolveUnicode.java");
	
	String str = cu.getSource();
	String selectAt = "java.lang.\\u0053tring";
	String selection = "java.lang.\\u0053tring";
	int start = str.indexOf(selectAt);
	int length = selection.length();
	IJavaElement[] elements = cu.codeSelect(start, length);
	
	assertTrue("should have one class 'java.lang.\u0053tring'", elements.length == 1);
	IJavaElement element = elements[0];
	assertTrue("should be an IType", element instanceof IType);
	IType type = (IType)element;
	assertEquals("unexpected element name", "\u0053tring", type.getElementName());
	assertEquals("unexpected parent name", "java.lang", type.getPackageFragment().getElementName());

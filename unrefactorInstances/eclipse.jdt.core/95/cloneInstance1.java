 */
public void testResolveQualifiedType() throws JavaModelException {
	ICompilationUnit cu = getCompilationUnit("Resolve", "src", "", "ResolveQualifiedType.java");
	
	String str = cu.getSource();
	String selectAt = "java.lang.Object";
	String selection = "java.lang.Object";
	int start = str.indexOf(selectAt);
	int length = selection.length();
	IJavaElement[] elements = cu.codeSelect(start, length);
	
	assertTrue("should have one class", elements.length == 1);
	IJavaElement element = elements[0];
	assertTrue("Should be an IType", element instanceof IType);
	IType type = (IType)element;
	assertTrue("should be java.lang.Object",
		type.getElementName().equals("Object") &&
		type.getPackageFragment().getElementName().equals("java.lang"));	

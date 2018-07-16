	public void test353() {
		this.runConformTest(
			new String[] {
				"X.java",
				"public class X extends Y {\n" + 
				"	<T> T foo(Class<T> c) { return null; }\n" +
				"}\n" + 
				"class Y {\n" + 
				"	<T> T foo(Class<T> c) { return null; }\n" +
				"}"
			},
			"");	
	}		

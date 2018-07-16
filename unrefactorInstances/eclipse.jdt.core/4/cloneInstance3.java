	public void test354() {
		this.runConformTest(
			new String[] {
				"X.java",
				"public class X extends Y {\n" + 
				"	<T, S> S foo(Class<T> c) { return null; }\n" +
				"}\n" + 
				"class Y {\n" + 
				"	<S, T> T foo(Class<S> c) { return null; }\n" +
				"}"
			},
			"");
	}		

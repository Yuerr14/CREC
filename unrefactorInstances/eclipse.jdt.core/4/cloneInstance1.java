	public void test286() {
		this.runConformTest(
			new String[] {
				"X.java",
				"public class X {\n" + 
				"	<T extends Object> T foo(Class<T> c) {return null;}\n" + 
				"}\n" + 
				"class Y extends X {\n" + 
				"	<T extends Object> T foo(Class<T> c) {return null;}\n" + 
				"}"
			},
			"");
	}	

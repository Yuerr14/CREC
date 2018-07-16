	public void test015() { // check behaviour of Scope.mostSpecificMethodBinding()
		this.runConformTest(
			new String[] {
				"X.java",
				"public class X {\n" +
				"	public static void main(String[] s) {\n" +
				"		Y.count(new int[0]);\n" + // for some reason this is not ambiguous
				"	}\n" +
				"}\n" +
				"class Y {\n" +
				"	public static void count(int[] array, int ... values) { System.out.print(1); }\n" +
				"	public static void count(int[] array, int[] ... values) { System.out.print(2); }\n" +
				"}\n",
			},
			"1"
		);
	}

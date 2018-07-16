public void test408038a() {
	this.runConformTest(
		new String[] {
			"externalizable/warning/X.java",
			"package externalizable.warning;\n" +
			"\n" +
			"public class X {\n" +
			"	private class Y {\n" +
			"		static final int i = 10;\n" +
			"		public Y() {}\n" +
			"		public Y(int x) {System.out.println(x);}\n" +
			"	}\n" +
			"\n" +
			"	public void zoo() {\n" +
			"		System.out.println(Y.i);\n" +
			"		Y y = new Y(5);\n" +
			"		System.out.println(y);\n" +
			"	}\n" +
			"}",
			},
			"\"" + OUTPUT_DIR +  File.separator + "externalizable" + File.separator + "warning" + File.separator + "X.java\""
			+ " -1.6 -d none",
			"",
			"----------\n" +
			"1. WARNING in ---OUTPUT_DIR_PLACEHOLDER---/externalizable/warning/X.java (at line 6)\n" +
			"	public Y() {}\n" +
			"	       ^^^\n" +
			"The constructor X.Y() is never used locally\n" +
			"----------\n" +
			"1 problem (1 warning)\n",
			true);
}

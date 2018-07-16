public void test152() {
	this.runConformTest(
		new String[] {
			"X.java",
			"public class X {\n" +
			"	static <T> void feedFoosValueIntoFoo(Foo<T> foo) {\n" +
			"		foo.doSomething(foo.getValue());\n" +
			"	}\n" +
			"	static void testTypedString() {\n" +
			"		ConcreteFoo foo = new ConcreteFoo();\n" +
			"		foo.doSomething(foo.getValue());\n" +
			"	}\n" +
			"	static void testGenericString() {\n" +
			"		feedFoosValueIntoFoo(new ConcreteFoo());\n" +
			"	}\n" +
			"	public static void main(String[] args) {\n" +
			"		testTypedString();\n" +
			"		testGenericString();\n" +
			"		System.out.print(1);\n" +
			"	}\n" +
			"}\n" +
			"interface Foo<T> {\n" +
			"	T getValue();\n" +
			"	void doSomething(T o);\n" +
			"}\n" +
			"abstract class AbstractFoo<T> implements Foo<T> {\n" +
			"	public void doSomething(String o) {}\n" +
			"}\n" +
			"class ConcreteFoo extends AbstractFoo<String> {\n" +
			"	public String getValue() { return null; }\n" +
			"}"
		},
		"1"
	);
}

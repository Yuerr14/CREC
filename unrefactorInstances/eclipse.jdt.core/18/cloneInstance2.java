public void test1303() {
	this.runConformTest(
			new String[] {
					"X.java",
					"public class X {\n" +
					"  interface Foo<T> {\n" +
					"    T getValue();\n" +
					"    void doSomething(T o);\n" +
					"  }\n" +
					"  public static abstract class AbstractFoo<T> implements Foo<T> {\n" +
					"    /**\n" +
					"     * If this is removed ConcreteFoo no longer compiles.\n" +
					"     */\n" +
					"    public void doSomething(final String o) {\n" +
					"    }\n" +
					"  }\n" +
					"  public static final class ConcreteFoo extends AbstractFoo<String> {\n" +
					"    public String getValue() {\n" +
					"      return \"I am a string\";\n" +
					"    }\n" +
					"  }\n" +
					"  /**\n" +
					"   * We lose the type infomation here so try but fail to call the doSomething(Object) method.\n" +
					"   */\n" +
					"  private static <T> void feedFoosValueIntoFoo(final Foo<T> foo) {\n" +
					"    foo.doSomething(foo.getValue());\n" +
					"  }\n" +
					"  private static void testTypedString() {\n" +
					"    final ConcreteFoo foo = new ConcreteFoo();\n" +
					"    foo.doSomething(foo.getValue());\n" +
					"  }\n" +
					"  private static void testGenericString() {\n" +
					"    feedFoosValueIntoFoo(new ConcreteFoo());\n" +
					"  }\n" +
					"  public static void main(String[] args) {\n" +
					"    testTypedString();\n" +
					"    testGenericString();\n" +
					"    System.out.println(\"SUCCESS\");\n" +
					"  }\n" +
					"}\n", // =================
			},
			"SUCCESS");
}

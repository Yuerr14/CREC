public void test048() throws Exception {
	this.runConformTest(
			new String[] {
				"X.java",
				"public class X {\n" +
				"	boolean bool() { return true; }\n" +
				"	void foo() {\n" +
				"		try {\n" +
				"			if (bool()) {\n" +
				"				return;\n" +
				"			}\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"	}\n" +
				"	int foo2() {\n" +
				"		try {\n" +
				"			while (bool()) {\n" +
				"				return 0;\n" +
				"			}\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"		return 1;\n" +
				"	}\n" +
				"	long foo3() {\n" +
				"		try {\n" +
				"			do {\n" +
				"				if (true) return 0L;\n" +
				"			} while (bool());\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"		return 1L;\n" +
				"	}	\n" +
				"	float foo4() {\n" +
				"		try {\n" +
				"			for (int i  = 0; bool(); i++) {\n" +
				"				return 0.0F;\n" +
				"			}\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"		return 1.0F;\n" +
				"	}		\n" +
				"	double bar() {\n" +
				"		if (bool()) {\n" +
				"			if (bool())\n" +
				"				return 0.0;\n" +
				"		} else {\n" +
				"			if (bool()) {\n" +
				"				throw new NullPointerException();\n" +
				"			}\n" +
				"		}\n" +
				"		return 1.0;\n" +
				"	}\n" +
				"	void baz(int i) {\n" +
				"		if (bool()) {\n" +
				"			switch(i) {\n" +
				"				case 0 : return;\n" +
				"				default : break;\n" +
				"			}\n" +
				"		} else {\n" +
				"			bool();\n" +
				"		}\n" +
				"	}\n" +
				"}\n",
			},
			"");

	String expectedOutput = new CompilerOptions(getCompilerOptions()).complianceLevel < ClassFileConstants.JDK1_6
		?	"  // Method descriptor #6 ()V\n" +
				"  // Stack: 1, Locals: 2\n" +
				"  void foo();\n" +
				"     0  aload_0 [this]\n" +
				"     1  invokevirtual X.bool() : boolean [17]\n" +
				"     4  ifeq 9\n" +
				"     7  return\n" +
				"     8  astore_1\n" +
				"     9  return\n" +
				"      Exception Table:\n" +
				"        [pc: 0, pc: 7] -> 8 when : java.lang.Exception\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 5]\n" +
				"        [pc: 7, line: 6]\n" +
				"        [pc: 8, line: 8]\n" +
				"        [pc: 9, line: 10]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 10] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #22 ()I\n" +
				"  // Stack: 1, Locals: 2\n" +
				"  int foo2();\n" +
				"     0  aload_0 [this]\n" +
				"     1  invokevirtual X.bool() : boolean [17]\n" +
				"     4  ifeq 10\n" +
				"     7  iconst_0\n" +
				"     8  ireturn\n" +
				"     9  astore_1\n" +
				"    10  iconst_1\n" +
				"    11  ireturn\n" +
				"      Exception Table:\n" +
				"        [pc: 0, pc: 7] -> 9 when : java.lang.Exception\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 13]\n" +
				"        [pc: 7, line: 14]\n" +
				"        [pc: 9, line: 16]\n" +
				"        [pc: 10, line: 18]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 12] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #24 ()J\n" +
				"  // Stack: 2, Locals: 1\n" +
				"  long foo3();\n" +
				"    0  lconst_0\n" +
				"    1  lreturn\n" +
				"    2  lconst_1\n" +
				"    3  lreturn\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 23]\n" +
				"        [pc: 2, line: 27]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 4] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #26 ()F\n" +
				"  // Stack: 1, Locals: 2\n" +
				"  float foo4();\n" +
				"     0  iconst_0\n" +
				"     1  istore_1 [i]\n" +
				"     2  aload_0 [this]\n" +
				"     3  invokevirtual X.bool() : boolean [17]\n" +
				"     6  ifeq 12\n" +
				"     9  fconst_0\n" +
				"    10  freturn\n" +
				"    11  astore_1\n" +
				"    12  fconst_1\n" +
				"    13  freturn\n" +
				"      Exception Table:\n" +
				"        [pc: 0, pc: 9] -> 11 when : java.lang.Exception\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 31]\n" +
				"        [pc: 9, line: 32]\n" +
				"        [pc: 11, line: 34]\n" +
				"        [pc: 12, line: 36]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 14] local: this index: 0 type: X\n" +
				"        [pc: 2, pc: 11] local: i index: 1 type: int\n" +
				"  \n" +
				"  // Method descriptor #30 ()D\n" +
				"  // Stack: 2, Locals: 1\n" +
				"  double bar();\n" +
				"     0  aload_0 [this]\n" +
				"     1  invokevirtual X.bool() : boolean [17]\n" +
				"     4  ifeq 16\n" +
				"     7  aload_0 [this]\n" +
				"     8  invokevirtual X.bool() : boolean [17]\n" +
				"    11  ifeq 31\n" +
				"    14  dconst_0\n" +
				"    15  dreturn\n" +
				"    16  aload_0 [this]\n" +
				"    17  invokevirtual X.bool() : boolean [17]\n" +
				"    20  ifeq 31\n" +
				"    23  new java.lang.NullPointerException [31]\n" +
				"    26  dup\n" +
				"    27  invokespecial java.lang.NullPointerException() [33]\n" +
				"    30  athrow\n" +
				"    31  dconst_1\n" +
				"    32  dreturn\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 39]\n" +
				"        [pc: 7, line: 40]\n" +
				"        [pc: 14, line: 41]\n" +
				"        [pc: 16, line: 43]\n" +
				"        [pc: 23, line: 44]\n" +
				"        [pc: 31, line: 47]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 33] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #35 (I)V\n" +
				"  // Stack: 1, Locals: 2\n" +
				"  void baz(int i);\n" +
				"     0  aload_0 [this]\n" +
				"     1  invokevirtual X.bool() : boolean [17]\n" +
				"     4  ifeq 32\n" +
				"     7  iload_1 [i]\n" +
				"     8  tableswitch default: 29\n" +
				"          case 0: 28\n" +
				"    28  return\n" +
				"    29  goto 37\n" +
				"    32  aload_0 [this]\n" +
				"    33  invokevirtual X.bool() : boolean [17]\n" +
				"    36  pop\n" +
				"    37  return\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 50]\n" +
				"        [pc: 7, line: 51]\n" +
				"        [pc: 28, line: 52]\n" +
				"        [pc: 29, line: 55]\n" +
				"        [pc: 32, line: 56]\n" +
				"        [pc: 37, line: 58]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 38] local: this index: 0 type: X\n" +
				"        [pc: 0, pc: 38] local: i index: 1 type: int\n"
		:
			"  // Method descriptor #6 ()V\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  void foo();\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 9\n" +
			"     7  return\n" +
			"     8  astore_1\n" +
			"     9  return\n" +
			"      Exception Table:\n" +
			"        [pc: 0, pc: 7] -> 8 when : java.lang.Exception\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 5]\n" +
			"        [pc: 7, line: 6]\n" +
			"        [pc: 8, line: 8]\n" +
			"        [pc: 9, line: 10]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 10] local: this index: 0 type: X\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 8, same_locals_1_stack_item, stack: {java.lang.Exception}]\n" +
			"        [pc: 9, same]\n" +
			"  \n" +
			"  // Method descriptor #23 ()I\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  int foo2();\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 10\n" +
			"     7  iconst_0\n" +
			"     8  ireturn\n" +
			"     9  astore_1\n" +
			"    10  iconst_1\n" +
			"    11  ireturn\n" +
			"      Exception Table:\n" +
			"        [pc: 0, pc: 7] -> 9 when : java.lang.Exception\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 13]\n" +
			"        [pc: 7, line: 14]\n" +
			"        [pc: 9, line: 16]\n" +
			"        [pc: 10, line: 18]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 12] local: this index: 0 type: X\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 9, same_locals_1_stack_item, stack: {java.lang.Exception}]\n" +
			"        [pc: 10, same]\n" +
			"  \n" +
			"  // Method descriptor #25 ()J\n" +
			"  // Stack: 2, Locals: 1\n" +
			"  long foo3();\n" +
			"    0  lconst_0\n" +
			"    1  lreturn\n" +
			"    2  lconst_1\n" +
			"    3  lreturn\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 23]\n" +
			"        [pc: 2, line: 27]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 4] local: this index: 0 type: X\n" +
			"      Stack map table: number of frames 1\n" +
			"        [pc: 2, same]\n" +
			"  \n" +
			"  // Method descriptor #27 ()F\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  float foo4();\n" +
			"     0  iconst_0\n" +
			"     1  istore_1 [i]\n" +
			"     2  aload_0 [this]\n" +
			"     3  invokevirtual X.bool() : boolean [17]\n" +
			"     6  ifeq 12\n" +
			"     9  fconst_0\n" +
			"    10  freturn\n" +
			"    11  astore_1\n" +
			"    12  fconst_1\n" +
			"    13  freturn\n" +
			"      Exception Table:\n" +
			"        [pc: 0, pc: 9] -> 11 when : java.lang.Exception\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 31]\n" +
			"        [pc: 9, line: 32]\n" +
			"        [pc: 11, line: 34]\n" +
			"        [pc: 12, line: 36]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 14] local: this index: 0 type: X\n" +
			"        [pc: 2, pc: 11] local: i index: 1 type: int\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 11, same_locals_1_stack_item, stack: {java.lang.Exception}]\n" +
			"        [pc: 12, same]\n" +
			"  \n" +
			"  // Method descriptor #31 ()D\n" +
			"  // Stack: 2, Locals: 1\n" +
			"  double bar();\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 16\n" +
			"     7  aload_0 [this]\n" +
			"     8  invokevirtual X.bool() : boolean [17]\n" +
			"    11  ifeq 31\n" +
			"    14  dconst_0\n" +
			"    15  dreturn\n" +
			"    16  aload_0 [this]\n" +
			"    17  invokevirtual X.bool() : boolean [17]\n" +
			"    20  ifeq 31\n" +
			"    23  new java.lang.NullPointerException [32]\n" +
			"    26  dup\n" +
			"    27  invokespecial java.lang.NullPointerException() [34]\n" +
			"    30  athrow\n" +
			"    31  dconst_1\n" +
			"    32  dreturn\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 39]\n" +
			"        [pc: 7, line: 40]\n" +
			"        [pc: 14, line: 41]\n" +
			"        [pc: 16, line: 43]\n" +
			"        [pc: 23, line: 44]\n" +
			"        [pc: 31, line: 47]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 33] local: this index: 0 type: X\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 16, same]\n" +
			"        [pc: 31, same]\n" +
			"  \n" +
			"  // Method descriptor #36 (I)V\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  void baz(int i);\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 32\n" +
			"     7  iload_1 [i]\n" +
			"     8  tableswitch default: 29\n" +
			"          case 0: 28\n" +
			"    28  return\n" +
			"    29  goto 37\n" +
			"    32  aload_0 [this]\n" +
			"    33  invokevirtual X.bool() : boolean [17]\n" +
			"    36  pop\n" +
			"    37  return\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 50]\n" +
			"        [pc: 7, line: 51]\n" +
			"        [pc: 28, line: 52]\n" +
			"        [pc: 29, line: 55]\n" +
			"        [pc: 32, line: 56]\n" +
			"        [pc: 37, line: 58]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 38] local: this index: 0 type: X\n" +
			"        [pc: 0, pc: 38] local: i index: 1 type: int\n" +
			"      Stack map table: number of frames 4\n" +
			"        [pc: 28, same]\n" +
			"        [pc: 29, same]\n" +
			"        [pc: 32, same]\n" +
			"        [pc: 37, same]\n";
	File f = new File(OUTPUT_DIR + File.separator + "X.class");
	byte[] classFileBytes = org.eclipse.jdt.internal.compiler.util.Util.getFileByteContent(f);
	ClassFileBytesDisassembler disassembler = ToolFactory.createDefaultClassFileBytesDisassembler();
	String result = disassembler.disassemble(classFileBytes, "\n", ClassFileBytesDisassembler.DETAILED);
	int index = result.indexOf(expectedOutput);
	if (index == -1 || expectedOutput.length() == 0) {
		System.out.println(Util.displayString(result, 3));
	}
	if (index == -1) {
		assertEquals("Wrong contents", expectedOutput, result);
	}
}

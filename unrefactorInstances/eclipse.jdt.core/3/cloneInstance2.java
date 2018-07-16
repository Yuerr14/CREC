public void test050() throws Exception {
	this.runConformTest(
			new String[] {
				"X.java",
				"public class X {\n" +
				"	boolean bool() { return true; }\n" +
				"	void foo() {\n" +
				"		check: try {\n" +
				"			if (bool()) {\n" +
				"				break check;\n" +
				"			}\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"	}\n" +
				"	void foo2() {\n" +
				"		check: try {\n" +
				"			while (bool()) {\n" +
				"				break check;\n" +
				"			}\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"	}\n" +
				"	void foo3() {\n" +
				"		check: try {\n" +
				"			do {\n" +
				"				if (true) break check;\n" +
				"			} while (bool());\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"	}	\n" +
				"	void foo4() {\n" +
				"		check: try {\n" +
				"			for (int i  = 0; bool(); i++) {\n" +
				"				break check;\n" +
				"			}\n" +
				"		} catch (Exception e) {\n" +
				"		}\n" +
				"	}\n" +
				"	void bar() {\n" +
				"		check: if (bool()) {\n" +
				"			if (bool())\n" +
				"				break check;\n" +
				"		} else {\n" +
				"			if (bool()) {\n" +
				"				break check;\n" +
				"			}\n" +
				"		}\n" +
				"	}\n" +
				"	void baz(int i) {\n" +
				"		check: if (bool()) {\n" +
				"			switch(i) {\n" +
				"				case 0 : break check;\n" +
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
				"     4  ifeq 11\n" +
				"     7  goto 11\n" +
				"    10  astore_1\n" +
				"    11  return\n" +
				"      Exception Table:\n" +
				"        [pc: 0, pc: 7] -> 10 when : java.lang.Exception\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 5]\n" +
				"        [pc: 7, line: 6]\n" +
				"        [pc: 10, line: 8]\n" +
				"        [pc: 11, line: 10]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 12] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #6 ()V\n" +
				"  // Stack: 1, Locals: 2\n" +
				"  void foo2();\n" +
				"     0  aload_0 [this]\n" +
				"     1  invokevirtual X.bool() : boolean [17]\n" +
				"     4  ifeq 11\n" +
				"     7  goto 11\n" +
				"    10  astore_1\n" +
				"    11  return\n" +
				"      Exception Table:\n" +
				"        [pc: 0, pc: 7] -> 10 when : java.lang.Exception\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 13]\n" +
				"        [pc: 7, line: 14]\n" +
				"        [pc: 10, line: 16]\n" +
				"        [pc: 11, line: 18]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 12] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #6 ()V\n" +
				"  // Stack: 0, Locals: 1\n" +
				"  void foo3();\n" +
				"    0  return\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 26]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 1] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #6 ()V\n" +
				"  // Stack: 1, Locals: 2\n" +
				"  void foo4();\n" +
				"     0  iconst_0\n" +
				"     1  istore_1 [i]\n" +
				"     2  aload_0 [this]\n" +
				"     3  invokevirtual X.bool() : boolean [17]\n" +
				"     6  ifeq 13\n" +
				"     9  goto 13\n" +
				"    12  astore_1\n" +
				"    13  return\n" +
				"      Exception Table:\n" +
				"        [pc: 0, pc: 9] -> 12 when : java.lang.Exception\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 29]\n" +
				"        [pc: 9, line: 30]\n" +
				"        [pc: 12, line: 32]\n" +
				"        [pc: 13, line: 34]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 14] local: this index: 0 type: X\n" +
				"        [pc: 2, pc: 12] local: i index: 1 type: int\n" +
				"  \n" +
				"  // Method descriptor #6 ()V\n" +
				"  // Stack: 1, Locals: 1\n" +
				"  void bar();\n" +
				"     0  aload_0 [this]\n" +
				"     1  invokevirtual X.bool() : boolean [17]\n" +
				"     4  ifeq 17\n" +
				"     7  aload_0 [this]\n" +
				"     8  invokevirtual X.bool() : boolean [17]\n" +
				"    11  ifeq 24\n" +
				"    14  goto 24\n" +
				"    17  aload_0 [this]\n" +
				"    18  invokevirtual X.bool() : boolean [17]\n" +
				"    21  ifeq 24\n" +
				"    24  return\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 36]\n" +
				"        [pc: 7, line: 37]\n" +
				"        [pc: 14, line: 38]\n" +
				"        [pc: 17, line: 40]\n" +
				"        [pc: 24, line: 44]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 25] local: this index: 0 type: X\n" +
				"  \n" +
				"  // Method descriptor #28 (I)V\n" +
				"  // Stack: 1, Locals: 2\n" +
				"  void baz(int i);\n" +
				"     0  aload_0 [this]\n" +
				"     1  invokevirtual X.bool() : boolean [17]\n" +
				"     4  ifeq 34\n" +
				"     7  iload_1 [i]\n" +
				"     8  tableswitch default: 31\n" +
				"          case 0: 28\n" +
				"    28  goto 39\n" +
				"    31  goto 39\n" +
				"    34  aload_0 [this]\n" +
				"    35  invokevirtual X.bool() : boolean [17]\n" +
				"    38  pop\n" +
				"    39  return\n" +
				"      Line numbers:\n" +
				"        [pc: 0, line: 46]\n" +
				"        [pc: 7, line: 47]\n" +
				"        [pc: 28, line: 48]\n" +
				"        [pc: 31, line: 51]\n" +
				"        [pc: 34, line: 52]\n" +
				"        [pc: 39, line: 54]\n" +
				"      Local variable table:\n" +
				"        [pc: 0, pc: 40] local: this index: 0 type: X\n" +
				"        [pc: 0, pc: 40] local: i index: 1 type: int\n"
		:
			"  // Method descriptor #6 ()V\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  void foo();\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 11\n" +
			"     7  goto 11\n" +
			"    10  astore_1\n" +
			"    11  return\n" +
			"      Exception Table:\n" +
			"        [pc: 0, pc: 7] -> 10 when : java.lang.Exception\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 5]\n" +
			"        [pc: 7, line: 6]\n" +
			"        [pc: 10, line: 8]\n" +
			"        [pc: 11, line: 10]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 12] local: this index: 0 type: X\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 10, same_locals_1_stack_item, stack: {java.lang.Exception}]\n" +
			"        [pc: 11, same]\n" +
			"  \n" +
			"  // Method descriptor #6 ()V\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  void foo2();\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 11\n" +
			"     7  goto 11\n" +
			"    10  astore_1\n" +
			"    11  return\n" +
			"      Exception Table:\n" +
			"        [pc: 0, pc: 7] -> 10 when : java.lang.Exception\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 13]\n" +
			"        [pc: 7, line: 14]\n" +
			"        [pc: 10, line: 16]\n" +
			"        [pc: 11, line: 18]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 12] local: this index: 0 type: X\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 10, same_locals_1_stack_item, stack: {java.lang.Exception}]\n" +
			"        [pc: 11, same]\n" +
			"  \n" +
			"  // Method descriptor #6 ()V\n" +
			"  // Stack: 0, Locals: 1\n" +
			"  void foo3();\n" +
			"    0  return\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 26]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 1] local: this index: 0 type: X\n" +
			"  \n" +
			"  // Method descriptor #6 ()V\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  void foo4();\n" +
			"     0  iconst_0\n" +
			"     1  istore_1 [i]\n" +
			"     2  aload_0 [this]\n" +
			"     3  invokevirtual X.bool() : boolean [17]\n" +
			"     6  ifeq 13\n" +
			"     9  goto 13\n" +
			"    12  astore_1\n" +
			"    13  return\n" +
			"      Exception Table:\n" +
			"        [pc: 0, pc: 9] -> 12 when : java.lang.Exception\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 29]\n" +
			"        [pc: 9, line: 30]\n" +
			"        [pc: 12, line: 32]\n" +
			"        [pc: 13, line: 34]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 14] local: this index: 0 type: X\n" +
			"        [pc: 2, pc: 12] local: i index: 1 type: int\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 12, same_locals_1_stack_item, stack: {java.lang.Exception}]\n" +
			"        [pc: 13, same]\n" +
			"  \n" +
			"  // Method descriptor #6 ()V\n" +
			"  // Stack: 1, Locals: 1\n" +
			"  void bar();\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 17\n" +
			"     7  aload_0 [this]\n" +
			"     8  invokevirtual X.bool() : boolean [17]\n" +
			"    11  ifeq 24\n" +
			"    14  goto 24\n" +
			"    17  aload_0 [this]\n" +
			"    18  invokevirtual X.bool() : boolean [17]\n" +
			"    21  ifeq 24\n" +
			"    24  return\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 36]\n" +
			"        [pc: 7, line: 37]\n" +
			"        [pc: 14, line: 38]\n" +
			"        [pc: 17, line: 40]\n" +
			"        [pc: 24, line: 44]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 25] local: this index: 0 type: X\n" +
			"      Stack map table: number of frames 2\n" +
			"        [pc: 17, same]\n" +
			"        [pc: 24, same]\n" +
			"  \n" +
			"  // Method descriptor #29 (I)V\n" +
			"  // Stack: 1, Locals: 2\n" +
			"  void baz(int i);\n" +
			"     0  aload_0 [this]\n" +
			"     1  invokevirtual X.bool() : boolean [17]\n" +
			"     4  ifeq 34\n" +
			"     7  iload_1 [i]\n" +
			"     8  tableswitch default: 31\n" +
			"          case 0: 28\n" +
			"    28  goto 39\n" +
			"    31  goto 39\n" +
			"    34  aload_0 [this]\n" +
			"    35  invokevirtual X.bool() : boolean [17]\n" +
			"    38  pop\n" +
			"    39  return\n" +
			"      Line numbers:\n" +
			"        [pc: 0, line: 46]\n" +
			"        [pc: 7, line: 47]\n" +
			"        [pc: 28, line: 48]\n" +
			"        [pc: 31, line: 51]\n" +
			"        [pc: 34, line: 52]\n" +
			"        [pc: 39, line: 54]\n" +
			"      Local variable table:\n" +
			"        [pc: 0, pc: 40] local: this index: 0 type: X\n" +
			"        [pc: 0, pc: 40] local: i index: 1 type: int\n" +
			"      Stack map table: number of frames 4\n" +
			"        [pc: 28, same]\n" +
			"        [pc: 31, same]\n" +
			"        [pc: 34, same]\n" +
			"        [pc: 39, same]\n";

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

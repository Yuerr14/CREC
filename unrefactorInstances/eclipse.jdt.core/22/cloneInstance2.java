	public void test436() {
		Map options = DefaultCodeFormatterConstants.getJavaConventionsSettings();
		DefaultCodeFormatterOptions preferences = new DefaultCodeFormatterOptions(options);
		preferences.brace_position_for_type_declaration = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.brace_position_for_method_declaration = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.brace_position_for_constructor_declaration = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.brace_position_for_block = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.blank_lines_before_first_class_body_declaration = 1;
        preferences.tab_char = DefaultCodeFormatterOptions.SPACE;
        preferences.tab_size = 4;
		DefaultCodeFormatter codeFormatter = new DefaultCodeFormatter(preferences);
		runTest(codeFormatter, "test436", "A.java", CodeFormatter.K_COMPILATION_UNIT);//$NON-NLS-1$ //$NON-NLS-2$
	}

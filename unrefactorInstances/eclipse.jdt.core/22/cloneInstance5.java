	public void test564() {
		Map options = DefaultCodeFormatterConstants.getJavaConventionsSettings();
		DefaultCodeFormatterOptions preferences = new DefaultCodeFormatterOptions(options);
        preferences.tab_char = DefaultCodeFormatterOptions.SPACE;
        preferences.tab_size = 4;
		preferences.brace_position_for_array_initializer = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.brace_position_for_type_declaration = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.brace_position_for_method_declaration = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.brace_position_for_block = DefaultCodeFormatterConstants.NEXT_LINE;
		preferences.keep_empty_array_initializer_on_one_line = false;
		DefaultCodeFormatter codeFormatter = new DefaultCodeFormatter(preferences);
		runTest(codeFormatter, "test564", "A.java", CodeFormatter.K_COMPILATION_UNIT, false);//$NON-NLS-1$ //$NON-NLS-2$
	}

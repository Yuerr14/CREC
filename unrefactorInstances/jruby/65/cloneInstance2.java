    private int parseQuote(int c) throws IOException {
        int begin, end;

        String value = "%" + (char) c;
        
        // Short-hand (e.g. %{,%.,%!,... versus %Q{).
        if (!Character.isLetterOrDigit(c)) {
            begin = c;
            c = 'Q';

        // Long-hand (e.g. %Q{}).
        } else {

            begin = nextc();
            value = value + (char) begin;
            if (Character.isLetterOrDigit(begin) || !isASCII()) {
                compile_error("unknown type of %string");
                return EOF;
            }
        }
        if (c == EOF || begin == EOF) {
            compile_error("unterminated quoted string meets end of file");
            return EOF;
        }
        
        // Figure end-char.  '\0' is special to indicate begin=end and that no nesting?
        switch(begin) {
        case '(': end = ')'; break;
        case '[': end = ']'; break;
        case '{': end = '}'; break;
        case '<': end = '>'; break;
        default: 
            end = begin; 
            begin = '\0';
        }

        // consume spaces here to record them as part of token
        int w = nextc();
        while (Character.isWhitespace(w)) {
            value = value + (char) w;
            w = nextc();
        }
        pushback(w);
        
        switch (c) {
        case 'Q':
            lex_strterm = new StringTerm(str_dquote, begin ,end);
            return Tokens.tSTRING_BEG;

        case 'q':
            lex_strterm = new StringTerm(str_squote, begin, end);
            return Tokens.tSTRING_BEG;

        case 'W':
            lex_strterm = new StringTerm(str_dquote | STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            return Tokens.tWORDS_BEG;

        case 'w':
            lex_strterm = new StringTerm(/* str_squote | */ STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            return Tokens.tQWORDS_BEG;

        case 'x':
            lex_strterm = new StringTerm(str_xquote, begin, end);
            return Tokens.tXSTRING_BEG;

        case 'r':
            lex_strterm = new StringTerm(str_regexp, begin, end);
            return Tokens.tREGEXP_BEG;

        case 's':
            lex_strterm = new StringTerm(str_ssym, begin, end);
            setState(EXPR_FNAME);
            return Tokens.tSYMBEG;

        case 'I':
            lex_strterm = new StringTerm(str_dquote | STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            return Tokens.tSYMBOLS_BEG;

        case 'i':
            lex_strterm = new StringTerm(/* str_squote | */STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            return Tokens.tQSYMBOLS_BEG;
        default:
            compile_error("Unknown type of %string. Expected 'Q', 'q', 'w', 'x', 'r' or any non letter character, but found '" + c + "'.");
            return -1; //notreached
        }
    }

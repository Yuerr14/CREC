    private int parseQuote(int c) throws IOException {
        int begin, end;
        boolean shortHand;
        
        // Short-hand (e.g. %{,%.,%!,... versus %Q{).
        if (!Character.isLetterOrDigit(c)) {
            begin = c;
            c = 'Q';
            shortHand = true;
        // Long-hand (e.g. %Q{}).
        } else {
            shortHand = false;
            begin = nextc();
            if (Character.isLetterOrDigit(begin) /* no mb || ismbchar(term)*/) compile_error(PID.STRING_UNKNOWN_TYPE, "unknown type of %string");
        }
        if (c == EOF || begin == EOF) compile_error(PID.STRING_HITS_EOF, "unterminated quoted string meets end of file");

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

        switch (c) {
        case 'Q':
            lex_strterm = new StringTerm(str_dquote, begin ,end);
            yaccValue = "%"+ (shortHand ? (""+end) : ("" + c + begin));
            return Tokens.tSTRING_BEG;

        case 'q':
            lex_strterm = new StringTerm(str_squote, begin, end);
            yaccValue = "%"+c+begin;
            return Tokens.tSTRING_BEG;

        case 'W':
            lex_strterm = new StringTerm(str_dquote | STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            yaccValue = "%"+c+begin;
            return Tokens.tWORDS_BEG;

        case 'w':
            lex_strterm = new StringTerm(/* str_squote | */ STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            yaccValue = "%"+c+begin;
            return Tokens.tQWORDS_BEG;

        case 'x':
            lex_strterm = new StringTerm(str_xquote, begin, end);
            yaccValue = "%"+c+begin;
            return Tokens.tXSTRING_BEG;

        case 'r':
            lex_strterm = new StringTerm(str_regexp, begin, end);
            yaccValue = "%"+c+begin;
            return Tokens.tREGEXP_BEG;

        case 's':
            lex_strterm = new StringTerm(str_ssym, begin, end);
            setState(EXPR_FNAME);
            yaccValue = "%"+c+begin;
            return Tokens.tSYMBEG;
        
        case 'I':
            lex_strterm = new StringTerm(str_dquote | STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            yaccValue = "%" + c + begin;
            return Tokens.tSYMBOLS_BEG;
        case 'i':
            lex_strterm = new StringTerm(/* str_squote | */STR_FUNC_QWORDS, begin, end);
            do {c = nextc();} while (Character.isWhitespace(c));
            pushback(c);
            yaccValue = "%" + c + begin;
            return Tokens.tQSYMBOLS_BEG;
        default:
            compile_error(PID.STRING_UNKNOWN_TYPE, "unknown type of %string");
        }
        return -1; // not-reached
    }

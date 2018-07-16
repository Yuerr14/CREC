    private int rightCurly() {
        conditionState.restart();
        cmdArgumentState.restart();
        setState(isOneEight ? org.jruby.lexer.yacc.RubyYaccLexer.LexState.EXPR_END : org.jruby.lexer.yacc.RubyYaccLexer.LexState.EXPR_ENDARG);
        yaccValue = new Token("}",getPosition());
        return Tokens.tRCURLY;
    }

    private int rightParen() {
        parenNest--;
        conditionState.restart();
        cmdArgumentState.restart();
        setState(isOneEight ? org.jruby.lexer.yacc.RubyYaccLexer.LexState.EXPR_END : org.jruby.lexer.yacc.RubyYaccLexer.LexState.EXPR_ENDFN);
        yaccValue = new Token(")", getPosition());
        return Tokens.tRPAREN;
    }

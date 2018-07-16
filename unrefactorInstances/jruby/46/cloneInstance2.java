  @Override public Object execute(ParserSupport support, RubyLexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                    support.checkExpression(((ParseNode)yyVals[-2+yyTop]));
                    support.checkExpression(((ParseNode)yyVals[0+yyTop]));

                    boolean isLiteral = ((ParseNode)yyVals[-2+yyTop]) instanceof FixnumParseNode && ((ParseNode)yyVals[0+yyTop]) instanceof FixnumParseNode;
                    yyVal = new DotParseNode(support.getPosition(((ParseNode)yyVals[-2+yyTop])), ((ParseNode)yyVals[-2+yyTop]), ((ParseNode)yyVals[0+yyTop]), true, isLiteral);
    return yyVal;
  }

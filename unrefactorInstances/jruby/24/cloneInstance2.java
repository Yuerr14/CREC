  public Object execute(ParserSupport support, RubyYaccLexer lexer, Object yyVal, Object[] yyVals, int yyTop) {
                  ISourcePosition pos = ((ListNode)yyVals[-4+yyTop]).getPosition();
                  yyVal = support.arg_concat(pos, support.newArrayNode(pos, new HashNode(pos, ((ListNode)yyVals[-4+yyTop]))), ((Node)yyVals[-1+yyTop]));
                  yyVal = support.arg_blk_pass((Node)yyVal, ((BlockPassNode)yyVals[0+yyTop]));
    return yyVal;
  }

public Object case410_line1573(Object yyVal, Object[] yyVals, int yyTop) {
                    int options = ((RegexpNode)yyVals[0+yyTop]).getOptions();
                    Node node = ((Node)yyVals[-1+yyTop]);

                    if (node == null) {
                        yyVal = new RegexpNode(getPosition(((Token)yyVals[-2+yyTop])), ByteList.create(""), options & ~ReOptions.RE_OPTION_ONCE);
                    } else if (node instanceof StrNode) {
                        yyVal = new RegexpNode(((Node)yyVals[-1+yyTop]).getPosition(), (ByteList) ((StrNode) node).getValue().clone(), options & ~ReOptions.RE_OPTION_ONCE);
                    } else if (node instanceof DStrNode) {
                        yyVal = new DRegexpNode(getPosition(((Token)yyVals[-2+yyTop])), (DStrNode) node, options, (options & ReOptions.RE_OPTION_ONCE) != 0);
                    } else {
                        yyVal = new DRegexpNode(getPosition(((Token)yyVals[-2+yyTop])), options, (options & ReOptions.RE_OPTION_ONCE) != 0).add(node);
                    }
    return yyVal;
}

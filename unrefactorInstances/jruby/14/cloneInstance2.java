    private int leftBracket(boolean spaceSeen) throws IOException {
        parenNest++;
        int c = '[';
        if (isAfterOperator()) {
            setState(EXPR_ARG);

            if ((c = nextc()) == ']') {
                if (peek('=')) {
                    nextc();
                    return Tokens.tASET;
                }
                return Tokens.tAREF;
            }
            pushback(c);
            setState(getState() | EXPR_LABEL);
            return '[';
        } else if (isBEG() || (isARG() && (spaceSeen || isLexState(lex_state, EXPR_LABELED)))) {
            c = Tokens.tLBRACK;
        }

        setState(EXPR_BEG|EXPR_LABEL);
        conditionState.stop();
        cmdArgumentState.stop();
        yaccValue = "[";
        return c;
    }

                            Character.isWhitespace(p(lex_p + 5))) {
                        for (;;) {
                            lex_goto_eol();

                            c = nextc();

                            if (c == EOF) {
                                compile_error("embedded document meets end of file");
                                return EOF;
                            }

                            if (c != '=') continue;

                            if (strncmp(lexb.makeShared(lex_p, lex_pend - lex_p), END_DOC_MARKER, END_DOC_MARKER.length()) &&
                                    (lex_p + 3 == lex_pend || Character.isWhitespace(p(lex_p + 3)))) {
                                break;
                            }
                        }
                        lex_goto_eol();

                        continue loop;
                    }

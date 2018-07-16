						for (int i = 1; i < expressionsLength; i++) {
							this.scribe.printNextToken(TerminalTokens.TokenNameCOMMA, this.preferences.insert_space_before_comma_in_array_initializer);
							this.scribe.alignFragment(arrayInitializerAlignment, i);
							if (this.preferences.insert_space_after_comma_in_array_initializer) {
								this.scribe.space();
							}
							expressions[i].traverse(this, scope);
							if (i == expressionsLength - 1) {
								if (isComma()) {
									this.scribe.printNextToken(TerminalTokens.TokenNameCOMMA, this.preferences.insert_space_before_comma_in_array_initializer);
								}
							}
						}

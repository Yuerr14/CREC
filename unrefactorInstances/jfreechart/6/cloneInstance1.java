                        if (dlg.open() != null) {
                            // Dispose of any fonts we have created
                            if (SWTAxisEditor.this.font != null) {
                                SWTAxisEditor.this.font.dispose();
                            }
                            // Create the new font and set it into the title
                            // label
                            SWTAxisEditor.this.font = new Font(
                                    getShell().getDisplay(), dlg.getFontList());
                            //label.setFont(font);
                            SWTAxisEditor.this.labelFontField.setText(
                                    SWTAxisEditor.this.font.getFontData()[0]
                                    .toString());
                            SWTAxisEditor.this.labelFont
                                    = SWTAxisEditor.this.font.getFontData()[0];
                        }

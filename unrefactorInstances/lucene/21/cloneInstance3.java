        private boolean r_standard_suffix() {
            int among_var;
            int v_1;
            int v_2;
            int v_3;
            int v_4;
            // (, line 76
            // [, line 77
            ket = cursor;
            // substring, line 77
            among_var = find_among_b(a_5, 45);
            if (among_var == 0)
            {
                return false;
            }
            // ], line 77
            bra = cursor;
            switch(among_var) {
                case 0:
                    return false;
                case 1:
                    // (, line 92
                    // call R2, line 93
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 93
                    slice_del();
                    break;
                case 2:
                    // (, line 97
                    // call R2, line 98
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 98
                    slice_from("log");
                    break;
                case 3:
                    // (, line 101
                    // call R2, line 102
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 102
                    slice_from("u");
                    break;
                case 4:
                    // (, line 105
                    // call R2, line 106
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 106
                    slice_from("ente");
                    break;
                case 5:
                    // (, line 109
                    // call R1, line 110
                    if (!r_R1())
                    {
                        return false;
                    }
                    // delete, line 110
                    slice_del();
                    // try, line 111
                    v_1 = limit - cursor;
                    lab0: do {
                        // (, line 111
                        // [, line 112
                        ket = cursor;
                        // substring, line 112
                        among_var = find_among_b(a_2, 4);
                        if (among_var == 0)
                        {
                            cursor = limit - v_1;
                            break lab0;
                        }
                        // ], line 112
                        bra = cursor;
                        // call R2, line 112
                        if (!r_R2())
                        {
                            cursor = limit - v_1;
                            break lab0;
                        }
                        // delete, line 112
                        slice_del();
                        switch(among_var) {
                            case 0:
                                cursor = limit - v_1;
                                break lab0;
                            case 1:
                                // (, line 113
                                // [, line 113
                                ket = cursor;
                                // literal, line 113
                                if (!(eq_s_b(2, "at")))
                                {
                                    cursor = limit - v_1;
                                    break lab0;
                                }
                                // ], line 113
                                bra = cursor;
                                // call R2, line 113
                                if (!r_R2())
                                {
                                    cursor = limit - v_1;
                                    break lab0;
                                }
                                // delete, line 113
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 6:
                    // (, line 121
                    // call R2, line 122
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 122
                    slice_del();
                    // try, line 123
                    v_2 = limit - cursor;
                    lab1: do {
                        // (, line 123
                        // [, line 124
                        ket = cursor;
                        // substring, line 124
                        among_var = find_among_b(a_3, 3);
                        if (among_var == 0)
                        {
                            cursor = limit - v_2;
                            break lab1;
                        }
                        // ], line 124
                        bra = cursor;
                        switch(among_var) {
                            case 0:
                                cursor = limit - v_2;
                                break lab1;
                            case 1:
                                // (, line 127
                                // call R2, line 127
                                if (!r_R2())
                                {
                                    cursor = limit - v_2;
                                    break lab1;
                                }
                                // delete, line 127
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 7:
                    // (, line 133
                    // call R2, line 134
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 134
                    slice_del();
                    // try, line 135
                    v_3 = limit - cursor;
                    lab2: do {
                        // (, line 135
                        // [, line 136
                        ket = cursor;
                        // substring, line 136
                        among_var = find_among_b(a_4, 3);
                        if (among_var == 0)
                        {
                            cursor = limit - v_3;
                            break lab2;
                        }
                        // ], line 136
                        bra = cursor;
                        switch(among_var) {
                            case 0:
                                cursor = limit - v_3;
                                break lab2;
                            case 1:
                                // (, line 139
                                // call R2, line 139
                                if (!r_R2())
                                {
                                    cursor = limit - v_3;
                                    break lab2;
                                }
                                // delete, line 139
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 8:
                    // (, line 145
                    // call R2, line 146
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 146
                    slice_del();
                    // try, line 147
                    v_4 = limit - cursor;
                    lab3: do {
                        // (, line 147
                        // [, line 148
                        ket = cursor;
                        // literal, line 148
                        if (!(eq_s_b(2, "at")))
                        {
                            cursor = limit - v_4;
                            break lab3;
                        }
                        // ], line 148
                        bra = cursor;
                        // call R2, line 148
                        if (!r_R2())
                        {
                            cursor = limit - v_4;
                            break lab3;
                        }
                        // delete, line 148
                        slice_del();
                    } while (false);
                    break;
                case 9:
                    // (, line 152
                    // call RV, line 153
                    if (!r_RV())
                    {
                        return false;
                    }
                    // literal, line 153
                    if (!(eq_s_b(1, "e")))
                    {
                        return false;
                    }
                    // <-, line 154
                    slice_from("ir");
                    break;
            }
            return true;
        }

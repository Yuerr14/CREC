        private boolean r_standard_suffix() {
            int among_var;
            int v_1;
            int v_2;
            int v_3;
            int v_4;
            int v_5;
            // (, line 86
            // [, line 87
            ket = cursor;
            // substring, line 87
            among_var = find_among_b(a_6, 46);
            if (among_var == 0)
            {
                return false;
            }
            // ], line 87
            bra = cursor;
            switch(among_var) {
                case 0:
                    return false;
                case 1:
                    // (, line 98
                    // call R2, line 99
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 99
                    slice_del();
                    break;
                case 2:
                    // (, line 104
                    // call R2, line 105
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 105
                    slice_del();
                    // try, line 106
                    v_1 = limit - cursor;
                    lab0: do {
                        // (, line 106
                        // [, line 106
                        ket = cursor;
                        // literal, line 106
                        if (!(eq_s_b(2, "ic")))
                        {
                            cursor = limit - v_1;
                            break lab0;
                        }
                        // ], line 106
                        bra = cursor;
                        // call R2, line 106
                        if (!r_R2())
                        {
                            cursor = limit - v_1;
                            break lab0;
                        }
                        // delete, line 106
                        slice_del();
                    } while (false);
                    break;
                case 3:
                    // (, line 110
                    // call R2, line 111
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 111
                    slice_from("log");
                    break;
                case 4:
                    // (, line 114
                    // call R2, line 115
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 115
                    slice_from("u");
                    break;
                case 5:
                    // (, line 118
                    // call R2, line 119
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 119
                    slice_from("ente");
                    break;
                case 6:
                    // (, line 122
                    // call R1, line 123
                    if (!r_R1())
                    {
                        return false;
                    }
                    // delete, line 123
                    slice_del();
                    // try, line 124
                    v_2 = limit - cursor;
                    lab1: do {
                        // (, line 124
                        // [, line 125
                        ket = cursor;
                        // substring, line 125
                        among_var = find_among_b(a_3, 4);
                        if (among_var == 0)
                        {
                            cursor = limit - v_2;
                            break lab1;
                        }
                        // ], line 125
                        bra = cursor;
                        // call R2, line 125
                        if (!r_R2())
                        {
                            cursor = limit - v_2;
                            break lab1;
                        }
                        // delete, line 125
                        slice_del();
                        switch(among_var) {
                            case 0:
                                cursor = limit - v_2;
                                break lab1;
                            case 1:
                                // (, line 126
                                // [, line 126
                                ket = cursor;
                                // literal, line 126
                                if (!(eq_s_b(2, "at")))
                                {
                                    cursor = limit - v_2;
                                    break lab1;
                                }
                                // ], line 126
                                bra = cursor;
                                // call R2, line 126
                                if (!r_R2())
                                {
                                    cursor = limit - v_2;
                                    break lab1;
                                }
                                // delete, line 126
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 7:
                    // (, line 134
                    // call R2, line 135
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 135
                    slice_del();
                    // try, line 136
                    v_3 = limit - cursor;
                    lab2: do {
                        // (, line 136
                        // [, line 137
                        ket = cursor;
                        // substring, line 137
                        among_var = find_among_b(a_4, 3);
                        if (among_var == 0)
                        {
                            cursor = limit - v_3;
                            break lab2;
                        }
                        // ], line 137
                        bra = cursor;
                        switch(among_var) {
                            case 0:
                                cursor = limit - v_3;
                                break lab2;
                            case 1:
                                // (, line 140
                                // call R2, line 140
                                if (!r_R2())
                                {
                                    cursor = limit - v_3;
                                    break lab2;
                                }
                                // delete, line 140
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 8:
                    // (, line 146
                    // call R2, line 147
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 147
                    slice_del();
                    // try, line 148
                    v_4 = limit - cursor;
                    lab3: do {
                        // (, line 148
                        // [, line 149
                        ket = cursor;
                        // substring, line 149
                        among_var = find_among_b(a_5, 3);
                        if (among_var == 0)
                        {
                            cursor = limit - v_4;
                            break lab3;
                        }
                        // ], line 149
                        bra = cursor;
                        switch(among_var) {
                            case 0:
                                cursor = limit - v_4;
                                break lab3;
                            case 1:
                                // (, line 152
                                // call R2, line 152
                                if (!r_R2())
                                {
                                    cursor = limit - v_4;
                                    break lab3;
                                }
                                // delete, line 152
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 9:
                    // (, line 158
                    // call R2, line 159
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 159
                    slice_del();
                    // try, line 160
                    v_5 = limit - cursor;
                    lab4: do {
                        // (, line 160
                        // [, line 161
                        ket = cursor;
                        // literal, line 161
                        if (!(eq_s_b(2, "at")))
                        {
                            cursor = limit - v_5;
                            break lab4;
                        }
                        // ], line 161
                        bra = cursor;
                        // call R2, line 161
                        if (!r_R2())
                        {
                            cursor = limit - v_5;
                            break lab4;
                        }
                        // delete, line 161
                        slice_del();
                    } while (false);
                    break;
            }
            return true;
        }

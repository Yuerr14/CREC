        private boolean r_standard_suffix() {
            int among_var;
            int v_1;
            int v_2;
            int v_3;
            int v_4;
            // (, line 103
            // [, line 104
            ket = cursor;
            // substring, line 104
            among_var = find_among_b(a_6, 51);
            if (among_var == 0)
            {
                return false;
            }
            // ], line 104
            bra = cursor;
            switch(among_var) {
                case 0:
                    return false;
                case 1:
                    // (, line 111
                    // call R2, line 111
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 111
                    slice_del();
                    break;
                case 2:
                    // (, line 113
                    // call R2, line 113
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 113
                    slice_del();
                    // try, line 114
                    v_1 = limit - cursor;
                    lab0: do {
                        // (, line 114
                        // [, line 114
                        ket = cursor;
                        // literal, line 114
                        if (!(eq_s_b(2, "ic")))
                        {
                            cursor = limit - v_1;
                            break lab0;
                        }
                        // ], line 114
                        bra = cursor;
                        // call R2, line 114
                        if (!r_R2())
                        {
                            cursor = limit - v_1;
                            break lab0;
                        }
                        // delete, line 114
                        slice_del();
                    } while (false);
                    break;
                case 3:
                    // (, line 117
                    // call R2, line 117
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 117
                    slice_from("log");
                    break;
                case 4:
                    // (, line 119
                    // call R2, line 119
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 119
                    slice_from("u");
                    break;
                case 5:
                    // (, line 121
                    // call R2, line 121
                    if (!r_R2())
                    {
                        return false;
                    }
                    // <-, line 121
                    slice_from("ente");
                    break;
                case 6:
                    // (, line 123
                    // call RV, line 123
                    if (!r_RV())
                    {
                        return false;
                    }
                    // delete, line 123
                    slice_del();
                    break;
                case 7:
                    // (, line 124
                    // call R1, line 125
                    if (!r_R1())
                    {
                        return false;
                    }
                    // delete, line 125
                    slice_del();
                    // try, line 126
                    v_2 = limit - cursor;
                    lab1: do {
                        // (, line 126
                        // [, line 127
                        ket = cursor;
                        // substring, line 127
                        among_var = find_among_b(a_4, 4);
                        if (among_var == 0)
                        {
                            cursor = limit - v_2;
                            break lab1;
                        }
                        // ], line 127
                        bra = cursor;
                        // call R2, line 127
                        if (!r_R2())
                        {
                            cursor = limit - v_2;
                            break lab1;
                        }
                        // delete, line 127
                        slice_del();
                        switch(among_var) {
                            case 0:
                                cursor = limit - v_2;
                                break lab1;
                            case 1:
                                // (, line 128
                                // [, line 128
                                ket = cursor;
                                // literal, line 128
                                if (!(eq_s_b(2, "at")))
                                {
                                    cursor = limit - v_2;
                                    break lab1;
                                }
                                // ], line 128
                                bra = cursor;
                                // call R2, line 128
                                if (!r_R2())
                                {
                                    cursor = limit - v_2;
                                    break lab1;
                                }
                                // delete, line 128
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 8:
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
                        among_var = find_among_b(a_5, 3);
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
                                // (, line 137
                                // call R2, line 137
                                if (!r_R2())
                                {
                                    cursor = limit - v_3;
                                    break lab2;
                                }
                                // delete, line 137
                                slice_del();
                                break;
                        }
                    } while (false);
                    break;
                case 9:
                    // (, line 141
                    // call R2, line 142
                    if (!r_R2())
                    {
                        return false;
                    }
                    // delete, line 142
                    slice_del();
                    // try, line 143
                    v_4 = limit - cursor;
                    lab3: do {
                        // (, line 143
                        // [, line 143
                        ket = cursor;
                        // literal, line 143
                        if (!(eq_s_b(2, "at")))
                        {
                            cursor = limit - v_4;
                            break lab3;
                        }
                        // ], line 143
                        bra = cursor;
                        // call R2, line 143
                        if (!r_R2())
                        {
                            cursor = limit - v_4;
                            break lab3;
                        }
                        // delete, line 143
                        slice_del();
                        // [, line 143
                        ket = cursor;
                        // literal, line 143
                        if (!(eq_s_b(2, "ic")))
                        {
                            cursor = limit - v_4;
                            break lab3;
                        }
                        // ], line 143
                        bra = cursor;
                        // call R2, line 143
                        if (!r_R2())
                        {
                            cursor = limit - v_4;
                            break lab3;
                        }
                        // delete, line 143
                        slice_del();
                    } while (false);
                    break;
            }
            return true;
        }

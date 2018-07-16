        static {
            TABLE = new char[256];
            for (int i = 0; i < 256; i++) {
                TABLE[i] = (char)i;
            }
            TABLE[0xA1] = '\u0104';
            TABLE[0xA2] = '\u0105';
            TABLE[0xA3] = '\u0141';
            TABLE[0xA4] = '\u20AC';
            TABLE[0xA5] = '\u201E';
            TABLE[0xA6] = '\u0160';
            TABLE[0xA8] = '\u0161';
            TABLE[0xAA] = '\u0218';
            TABLE[0xAC] = '\u0179';
            TABLE[0xAE] = '\u017A';
            TABLE[0xAF] = '\u017B';

            TABLE[0xB2] = '\u010C';
            TABLE[0xB3] = '\u0142';
            TABLE[0xB4] = '\u017D';
            TABLE[0xB5] = '\u201D';
            TABLE[0xB8] = '\u017E';
            TABLE[0xB9] = '\u010D';
            TABLE[0xBA] = '\u0219';
            TABLE[0xBC] = '\u0152';
            TABLE[0xBD] = '\u0153';
            TABLE[0xBE] = '\u0178';
            TABLE[0xBF] = '\u017C';

            TABLE[0xC3] = '\u0102';
            TABLE[0xC5] = '\u0106';

            TABLE[0xD1] = '\u0110';
            TABLE[0xD2] = '\u0143';
            TABLE[0xD5] = '\u0150';
            TABLE[0xD7] = '\u015A';
            TABLE[0xD8] = '\u0170';
            TABLE[0xDD] = '\u0118';
            TABLE[0xDE] = '\u021A';

            TABLE[0xE3] = '\u0103';
            TABLE[0xE5] = '\u0107';
        }

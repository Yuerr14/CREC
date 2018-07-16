    private String formatZone(int colons, int value, RubyTimeOutputFormatter formatter) {
        int seconds = Math.abs(value);
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;

        if (value < 0 && hours != 0) { // see below when hours == 0
            hours = -hours;
        }

        String mm = RubyTimeOutputFormatter.formatNumber(minutes, 2, '0');
        String ss = RubyTimeOutputFormatter.formatNumber(seconds, 2, '0');

        char padder = formatter.getPadder('0');
        int defaultWidth = -1;
        String after = null;

        switch (colons) {
            case 0: // %z -> +hhmm
                defaultWidth = 5;
                after = mm;
                break;
            case 1: // %:z -> +hh:mm
                defaultWidth = 6;
                after = ":" + mm;
                break;
            case 2: // %::z -> +hh:mm:ss
                defaultWidth = 9;
                after = ":" + mm + ":" + ss;
                break;
            case 3: // %:::z -> +hh[:mm[:ss]]
                StringBuilder sb = new StringBuilder();
                if (minutes != 0 || seconds != 0) sb.append(":").append(mm);
                if (seconds != 0) sb.append(":").append(ss);
                after = sb.toString();
                defaultWidth = after.length() + 3;
                break;
        }

        int minWidth = defaultWidth - 1;
        int width = formatter.getWidth(defaultWidth);
        if (width < minWidth) {
            width = minWidth;
        }
        width -= after.length();
        String before = RubyTimeOutputFormatter.formatSignedNumber(hours, width, padder);

        if (value < 0 && hours == 0) // the formatter could not handle this case
            before = before.replace('+', '-');
        return before + after;
    }

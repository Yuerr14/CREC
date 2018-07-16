    private String formatZone(int colons, int value, TimeOutputFormatter formatter) {
        int seconds = Math.abs(value);
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;

        if (value < 0 && hours != 0) { // see below when hours == 0
            hours = -hours;
        }

        String mm = TimeOutputFormatter.formatNumber(minutes, 2, '0');
        String ss = TimeOutputFormatter.formatNumber(seconds, 2, '0');

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
                if (minutes == 0) {
                    if (seconds == 0) { // +hh
                        defaultWidth = 3;
                        after = "";
                    } else { // +hh:mm
                        return formatZone(1, value, formatter);
                    }
                } else { // +hh:mm:ss
                    return formatZone(2, value, formatter);
                }
                break;
        }

        int minWidth = defaultWidth - 1;
        int width = formatter.getWidth(defaultWidth);
        if (width < minWidth) {
            width = minWidth;
        }
        width -= after.length();
        String before = TimeOutputFormatter.formatSignedNumber(hours, width, padder);

        if (value < 0 && hours == 0) // the formatter could not handle this case
            before = before.replace('+', '-');
        return before + after;
    }

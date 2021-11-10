package mepco.ca.util;

public class Text {
    public static final String fString(final String input, int fSize) {
        if (input==null) {
            return fString("", fSize);
        }

        if (input.length() < fSize) {
            final StringBuilder sb = new StringBuilder();
            for(int i=input.length(); i<fSize; i++) {
                sb.append(' ');
            }
            return input + sb.toString();
        } else {
            return input.substring(0, fSize);
        }
    }
}

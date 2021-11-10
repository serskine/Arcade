package mepco.ca.util.draw;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RetroChar {
    A('A', new String[] {
        " xxx ",
        "x   x",
        "xxxxx",
        "x   x",
        "x   x"
    }),
    B('B', new String[] {
            "xxxx ",
            "x   x",
            "xxxx ",
            "x   x",
            "xxxx "
    }),
    C('C', new String[] {
            " xxxx",
            "x    ",
            "x    ",
            "x    ",
            " xxxx"
    }),
    D('D', new String[] {
            "xxxx ",
            "x   x",
            "x   x",
            "x   x",
            "xxxx "
    }),
    E('E', new String[] {
            "xxxxx",
            "x    ",
            "xxx  ",
            "x    ",
            "xxxxx"
    }),
    F('F', new String[] {
            "xxxxx",
            "x    ",
            "xxx  ",
            "x    ",
            "x    "
    }),
    G('G', new String[] {
            " xxxx",
            "x    ",
            "x  xx",
            "x   x",
            " xxxx"
    }),
    H('H', new String[] {
            "x   x",
            "x   x",
            "xxxxx",
            "x   x",
            "x   x"
    }),
    I('I', new String[] {
            "xxxxx",
            "  x  ",
            "  x  ",
            "  x  ",
            "xxxxx"
    }),
    J('J', new String[] {
            "xxxxx",
            "   x ",
            "   x ",
            "x  x ",
            " xx  "
    }),
    K('K', new String[] {
            "x   x",
            "x  x ",
            "xxx  ",
            "x  x ",
            "x   x"
    }),
    L('L', new String[] {
            "x    ",
            "x    ",
            "x    ",
            "x    ",
            "xxxxx"
    }),
    M('M', new String[] {
            "x   x",
            "xx xx",
            "x x x",
            "x   x",
            "x   x"
    }),
    N('N', new String[] {
            "x   x",
            "xx  x",
            "x x x",
            "x  xx",
            "x   x"
    }),
    O('O', new String[] {
            " xxx ",
            "x   x",
            "x   x",
            "x   x",
            " xxx "
    }),
    P('P', new String[] {
            "xxxx ",
            "x   x",
            "xxxx ",
            "x    ",
            "x    "
    }),
    Q('Q', new String[] {
            " xxx ",
            "x   x",
            "x   x",
            "x  x ",
            " xx x"
    }),
    R('R', new String[] {
            "xxxx ",
            "x   x",
            "xxxx ",
            "x   x",
            "x   x"
    }),
    S('S', new String[] {
            " xxxx",
            "x    ",
            " xxx ",
            "    x",
            "xxxx "
    }),
    T('T', new String[] {
            "xxxxx",
            "  x  ",
            "  x  ",
            "  x  ",
            "  x  "
    }),
    U('U', new String[] {
            "x   x",
            "x   x",
            "x   x",
            "x   x",
            " xxx "
    }),
    V('V', new String[] {
            "x   x",
            "x   x",
            "x   x",
            " x x ",
            "  x  "
    }),
    W('W', new String[] {
            "x   x",
            "x   x",
            "x x x",
            "xx xx",
            "x   x"
    }),
    X('X', new String[] {
            "x   x",
            " x x ",
            "  x  ",
            " x x ",
            "x   x"
    }),
    Y('Y', new String[] {
            "x   x",
            "x   x",
            " x x ",
            "  x  ",
            "  x  "
    }),
    Z('Z', new String[] {
            "xxxxx",
            "   x ",
            "  x  ",
            " x   ",
            "xxxxx"
    }),
    SPACE(' ', new String[] {
            "     ",
            "     ",
            "     ",
            "     ",
            "     "
    }),
    PERIOD('.', new String[] {
            "     ",
            "     ",
            "     ",
            "     ",
            "  x  "
    }),
    QUESTION('?', new String[] {
            " xxx ",
            "x   x",
            "   x ",
            "     ",
            "  x  "
    }),
    EXCLAMATION('!', new String[] {
            "  x  ",
            "  x  ",
            "  x  ",
            "     ",
            "  x  "
    }),
    N0('0', new String[] {
            "xxxxx",
            "x   x",
            "x   x",
            "x   x",
            "xxxxx"
    }),
    N1('1', new String[] {
            " xx  ",
            "x x  ",
            "  x  ",
            "  x  ",
            "xxxxx"
    }),
    N2('2', new String[] {
            "xxxxx",
            "    x",
            "xxxxx",
            "x    ",
            "xxxxx"
    }),
    N3('3', new String[] {
            "xxxxx",
            "    x",
            "  xxx",
            "    x",
            "xxxxx"
    }),
    N4('4', new String[] {
            "x   x",
            "x   x",
            "xxxxx",
            "    x",
            "    x"
    }),
    N5('5', new String[] {
            "xxxxx",
            "x    ",
            "xxxxx",
            "    x",
            "xxxxx"
    }),
    N6('6', new String[] {
            "xxxxx",
            "x    ",
            "xxxxx",
            "x   x",
            "xxxxx"
    }),
    N7('7', new String[] {
            "xxxxx",
            "    x",
            "   x ",
            "  x  ",
            " x   "
    }),
    N8('8', new String[] {
            "xxxxx",
            "x   x",
            "xxxxx",
            "x   x",
            "xxxxx"
    }),
    N9('9', new String[] {
            "xxxxx",
            "x   x",
            "xxxxx",
            "    x",
            "    x"
    }),
    AT('@', new String[] {
            " xxx ",
            "x   x",
            "x xx ",
            "x x x",
            " xxx "
    }),
    POUND('#', new String[] {
            " x x ",
            "xxxxx",
            " x x ",
            "xxxxx",
            " x x "
    }),
    DOLLAR('$', new String[] {
            " xxxx",
            "x x  ",
            " xxx ",
            "  x x",
            "xxxx "
    }),
    PERCENT('%', new String[] {
            "xx  x",
            "xx x ",
            "  x  ",
            " x xx",
            "x  xx"
    }),
    HAT('^', new String[] {
            "  x  ",
            " x x ",
            "     ",
            "     ",
            "     "
    }),
    AND('&', new String[] {
            " xx  ",
            "x  x ",
            " xx  ",
            "x  x ",
            " xx x"
    }),
    STAR(' ', new String[] {
            "x x x",
            " xxx ",
            "xxxxx",
            " xxx ",
            "x x x"
    }),
    OPEN_BRACKET('(', new String[] {
            " xx  ",
            "x    ",
            "x    ",
            "x    ",
            " xx  "
    }),
    CLOSE_BRACKET(')', new String[] {
            "  xx ",
            "    x",
            "    x",
            "    x",
            "  xx "
    }),
    UNDERSCORE('_', new String[] {
            "     ",
            "     ",
            "     ",
            "     ",
            "xxxxx"
    }),
    MINUS('-', new String[] {
            "     ",
            "     ",
            "xxxxx",
            "     ",
            "     "
    }),
    EQUALS('=', new String[] {
            "     ",
            "xxxxx",
            "     ",
            "xxxxx",
            "     "
    }),
    PLUS(' ', new String[] {
            "  x  ",
            "  x  ",
            "xxxxx",
            "  x  ",
            "  x  "
    }),
    OPEN_BRACE('{', new String[] {
            " xx  ",
            "x    ",
            " x   ",
            "x    ",
            " xx  "
    }),
    CLOSE_BRACE(' ', new String[] {
            "  xx ",
            "    x",
            "   x ",
            "    x",
            "  xx "
    }),
    OPEN_SQUARE_BRACE('[', new String[] {
            "xxx  ",
            "x    ",
            "x    ",
            "x    ",
            "xxx  "
    }),
    CLOSE_SQUARE_BRACE(']', new String[] {
            "  xxx",
            "    x",
            "    x",
            "    x",
            "  xxx"
    }),
    OR('|', new String[] {
            "  x  ",
            "  x  ",
            "  x  ",
            "  x  ",
            "  x  "
    }),
    BACK_SLASH('/', new String[] {
            "    x",
            "   x ",
            "  x  ",
            " x   ",
            "x    "
    }),
    FORWARD_SLASH(' ', new String[] {
            "x    ",
            " x   ",
            "  x  ",
            "   x ",
            "    x"
    }),
    COLON(':', new String[] {
            "     ",
            "  x  ",
            "     ",
            "  x  ",
            "     "
    }),
    SEMI_COLON(';', new String[] {
            "     ",
            "  x  ",
            "     ",
            "  x  ",
            " x   "
    }),
    DOUBLE_QUOTE('"', new String[] {
            " x x ",
            " x x ",
            "     ",
            "     ",
            "     "
    }),
    SINGLE_QUOTE('\'', new String[] {
            "  x  ",
            "  x  ",
            "     ",
            "     ",
            "     "
    }),
    LESS_THAN('<', new String[] {
            "   xx",
            " xx  ",
            "x    ",
            " xx  ",
            "   xx"
    }),
    GREATER_THAN('>', new String[] {
            "xx   ",
            "  xx ",
            "    x",
            "  xx ",
            "xx   "
    }),
    COMMA(',', new String[] {
            "     ",
            "     ",
            "     ",
            "  x  ",
            " x   "
    }),
    SQUIGGLE('~', new String[] {
            "     ",
            " x   ",
            "x x x",
            "   x ",
            "     "
    }),
    APOSTROPHE(' ', new String[] {
            " xx  ",
            "  x  ",
            "     ",
            "     ",
            "     "
    })








    ;

    public final char lower;
    public final char upper;
    public final String[] map;
//    public final boolean[][] flags; // row then col for easier printing

    private RetroChar(char c, String[] map) {
        this.lower = Character.toLowerCase(c);
        this.upper = Character.toUpperCase(c);
        this.map = map;

    }

    public boolean isMatch(char c) {
        return  (   (lower == Character.toLowerCase(c))
                ||  (upper == Character.toUpperCase(c))
                );
    }

    boolean flag(int row, int col) {
        boolean[] rowFlags = rowFlags(row);
        return rowFlags[col];
    }

    boolean[] rowFlags(int row) {
        return rowFlags(map[row]);
    };

    static boolean[] rowFlags(String r) {
        boolean[] flags = new boolean[r.length()];
        for(int i=0; i<r.length(); i++) {
            char c = r.charAt(i);
            flags[i] = (c==' ') ? false : true;
        }
        return flags;
    }

    boolean[][] flags() {
        boolean[][] flags = new boolean[map.length][];
        for(int row=0; row<flags.length; row++) {
            flags[row] = rowFlags(row);
        }
        return flags;
    }

    public static List<RetroChar> getRetroChars(final String text, RetroChar defaultValue) {
        final List<RetroChar> theList = new ArrayList<>();
        if (text==null || text.length()<1) {
            return theList;
        }
        for(int i=0; i<text.length(); i++) {
            final char c = text.charAt(i);
            final RetroChar rc = Arrays.stream(RetroChar.values()).sequential().filter(x -> x.isMatch(c)).findFirst().orElse(defaultValue);
            theList.add(rc);
        }
        return theList;
    }

    public static String[] textRows(final List<RetroChar> chars) {
        final List<String> rowsList = new ArrayList<>();

        int width = 0;
        boolean isFirstRc = true;
        for(RetroChar rc : chars) {
            // Ensure there is a row to match everything
            if (rc.map.length > rowsList.size()) {
                for(int i=rowsList.size() ; i<rc.map.length; i++) {
                    String pad = "";
                    for(int j=0; j<width; j++) {
                        pad += " ";
                    }
                    rowsList.add(pad);
                }
            }

            for(int r=0; r<rowsList.size(); r++) {
                String current = rowsList.get(r);
                String append = rc.map[r];
                if (!isFirstRc) {
                    append = " " + append;
                }
                current += append;
                width = Math.max(current.length(), width);
                rowsList.set(r, current);
            }
            isFirstRc = false;
        }
        final String[] rows = new String[rowsList.size()];
        for(int r=0; r<rows.length; r++) {
            rows[r] = rowsList.get(r);
        }
        return rows;
    }

    public static boolean[][] getFlagsOf(final String text, RetroChar defaultValue) {
        final List<RetroChar> retroChars = getRetroChars(text, defaultValue);
        final String[] textRows = textRows(retroChars);
        final boolean[][] flags = new boolean[textRows.length][];
        for(int r=0; r<textRows.length; r++) {
            flags[r] = rowFlags(textRows[r]);
        }
        return flags;
    }

    public static final String allUpper() {
        final StringBuilder sb = new StringBuilder();
        for(RetroChar rc : RetroChar.values()) {
            sb.append(rc.upper);
        }
        return sb.toString();
    }


    public static final String allLower() {
        final StringBuilder sb = new StringBuilder();
        for(RetroChar rc : RetroChar.values()) {
            sb.append(rc.lower);
        }
        return sb.toString();
    }

}

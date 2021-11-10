package mepco.ca.util.draw;

import mepco.ca.util.Logger;
import mepco.ca.util.Util;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RetroCharTest {

    public static final String TEXT = "Hello World";




    @Test
    public void getRetroChars() {
        final List<RetroChar> observed = RetroChar.getRetroChars(TEXT, RetroChar.SPACE);
        Logger.info(Util.describeList(observed));
    }

    @Test
    public void textRows() {
        final List<RetroChar> observed = RetroChar.getRetroChars(TEXT, RetroChar.SPACE);
        Logger.info(Util.describeList(observed));

        String[] textRows = RetroChar.textRows(observed);
        Logger.info(Util.describeList(Arrays.asList(textRows), false));
    }

    @Test
    public void getFlagsOf() {

        final int expectedHeight = 5;
        final int expectedWidth = TEXT.length() + Math.max(0, TEXT.length()-1);

        final boolean[][] flags = RetroChar.getFlagsOf("Hello World", RetroChar.SPACE);

        Logger.info(describeFlags(flags));

        assertNotNull(flags);
        assertEquals(expectedHeight, flags.length);



        for(int r=0; r<flags.length; r++) {
            final boolean[] row = flags[r];
            Logger.info(Util.describeList(Arrays.asList(row)));

//            assertNotNull(row);
//            assertEquals(expectedWidth, row.length);
        }
    }

    String describeFlags(boolean[][] flags) {
        final String TRUE_TEXT = "[Y]";
        final String FALSE_TEXT = "[N]";
        StringBuilder sb = new StringBuilder();
        sb.append("(" + flags.length + " rows) -> {\n");
        for(int row = 0; row<flags.length; row++) {
            sb.append(" (" + flags[row].length + " cols) -> {");
            for(int col=0; col<flags[row].length; col++) {
                if (col>0) {
                    sb.append(", ");
                }
                sb.append((flags[row][col]==true ? TRUE_TEXT : FALSE_TEXT));
            }

            sb.append("}\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}

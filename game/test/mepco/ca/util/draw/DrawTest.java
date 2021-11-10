package mepco.ca.util.draw;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrawTest {

    public static final int SPACING = 10;
    public static final int CHARACTER_HEIGHT = SPACING * 5;
    public static final int CHARACTER_WIDTH = SPACING * 6;

    @Test
    public void renderRetroText() {
        final String text = "Hello World";
        final Dimension observed = Draw.getTextSize(null, CHARACTER_HEIGHT, text);

        final int expectedWidth = 11 * CHARACTER_WIDTH;
        final int expectedHeight = CHARACTER_HEIGHT;

        assertEquals(expectedWidth, observed.width);
        assertEquals(expectedHeight, observed.height);
    }


}

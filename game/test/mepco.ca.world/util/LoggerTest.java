package mepco.ca.world.util;

import mepco.ca.util.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoggerTest {

    @Test
    public void testInfo() {
        final StackTraceElement sBlank = Logger.getStackTraceElement();         // Do not move this line from line 13
        final StackTraceElement s0 = Logger.getStackTraceElement(0);      // Do not move this line from line 14

        assertEquals(13, sBlank.getLineNumber());
        assertEquals(this.getClass().getSimpleName()+".java", sBlank.getFileName());

        assertEquals(14, s0.getLineNumber());
        assertEquals(this.getClass().getSimpleName()+".java", s0.getFileName());
    }
}

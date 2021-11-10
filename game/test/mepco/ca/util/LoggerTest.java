package mepco.ca.util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggerTest {

    @Test
    public void testInfo() {
        final StackTraceElement sBlank = Logger.getStackTraceElement();         // Do not move this line from line 9
        final StackTraceElement s0 = Logger.getStackTraceElement(0);      // Do not move this line from line 10

        assertEquals(9, sBlank.getLineNumber());
        assertEquals(this.getClass().getSimpleName()+".java", sBlank.getFileName());

        assertEquals(10, s0.getLineNumber());
        assertEquals(this.getClass().getSimpleName()+".java", s0.getFileName());
    }
}

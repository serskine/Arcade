package mepco.ca.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AngleTest {

    public static final double ACCEPTABLE_ERROR = 0.00001D;
    @Test
    public void testMath() {
        final Angle a = new Angle(Math.PI);

        Logger.info(a.toString());

        assertEquals(a, new Angle(a));
        assertEquals(Math.PI, a.getRadians(), ACCEPTABLE_ERROR);
        assertEquals(180D, a.getDegrees(), ACCEPTABLE_ERROR);

        assertEquals(
            235D,
            new Angle(25D, true).add(new Angle(210D, true)).getDegrees(),
                ACCEPTABLE_ERROR
        );

        final Angle bigA = new Angle(360*10001D, true);
        assertEquals(0D, bigA.normalize().getDegrees(), ACCEPTABLE_ERROR);


    }
}

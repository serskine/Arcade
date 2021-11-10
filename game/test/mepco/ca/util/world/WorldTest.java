package mepco.ca.util.world;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static mepco.ca.util.world.FiniteWorld.coordinateString;
import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

    public static final String DEFAULT = " ";
    private World<String> world;

    @BeforeEach
    public void onSetup() {
        world = new InfiniteWorld<>(DEFAULT, 3);
    }

    @AfterEach
    public void onTearDown() {

    }

    @Test
    public void testSetup() {
        assertNotNull(world);
    }

    @Test
    public void calcSize() {
        assertEquals(125, FiniteWorld.calcSize(5, 5, 5));
        assertEquals((2*3*13), FiniteWorld.calcSize(2, 3, 13));
    }

    @Test
    public void getDimensions() {
        final Dimension[] dim = world.getDimensions();

        assertEquals(3, dim.length);
        assertEquals(3, world.getNumDimensions());
    }

    private static void assertArrayEquals(int[] expected, int[] observed) {
        if (expected==null) {
            assertNull(observed);
        } else {
            assertEquals(coordinateString(expected), coordinateString(observed));
        }
    }

    @Test
    public void location() {

        world.location(0, 0, 0).set("0,0,0");
        world.location(0, 0, 1).set("0,0,1");
        world.location(0, 1, 0).set("0,1,0");
        world.location(0, 1, 1).set("0,1,1");
        world.location(1, 0, 0).set("1,0,0");
        world.location(1, 0, 1).set("1,0,1");
        world.location(1, 1, 0).set("1,1,0");
        world.location(1, 1, 1).set("1,1,1");

        assertEquals("0,0,0", world.location(0,0,0).getValue());
        assertEquals("0,0,1", world.location(0,0,1).getValue());
        assertEquals("0,1,0", world.location(0,1,0).getValue());
        assertEquals("0,1,1", world.location(0,1,1).getValue());
        assertEquals("1,0,0", world.location(1,0,0).getValue());
        assertEquals("1,0,1", world.location(1,0,1).getValue());
        assertEquals("1,1,0", world.location(1,1,0).getValue());
        assertEquals("1,1,1", world.location(1,1,1).getValue());
    }


    @Test
    public void validateDimensionTooSmall() {
        final int dimension = -1;
        try {
            world.validateDimension(dimension);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Dimension " + dimension + " does not exist. There are " + world.getNumDimensions() + " dimensions starting at index 0.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateDimensionTooLarge() {
        final int dimension = 3;
        try {
            world.validateDimension(dimension);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Dimension " + dimension + " does not exist. There are " + world.getNumDimensions() + " dimensions starting at index 0.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void validateCoordinate_notAllDimensionsSpecified() {
        final int[] coordinate = new int[]{0, 0};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "A coordinate must specify exactly " + world.getNumDimensions() + " dimensions.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void validateCoordinate_tooManyDimensionsSpecified() {
        final int[] coordinate = new int[]{0, 0, 0, 0};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "A coordinate must specify exactly " + world.getNumDimensions() + " dimensions.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateDimensions_cantBeEmpty() {
        try {
            world.validateDimensions(InfiniteWorld.buildInfiniteDimensions(0));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("There must be at least one dimension.", e.getMessage());
        }
    }


    @Test
    public void getNeighbours() {
        final List<int[]> n = world.getNeighbours(0,0,0);

        n.stream().forEach(c -> {
            System.out.println(coordinateString(c));
        });

        assertTrue(World.contains(n, -1,-1,-1));
        assertTrue(World.contains(n, -1,-1,0));
        assertTrue(World.contains(n, -1,-1,1));
        assertTrue(World.contains(n, -1,0,-1));
        assertTrue(World.contains(n, -1,0,0));
        assertTrue(World.contains(n, -1,0,1));
        assertTrue(World.contains(n, -1,1,-1));
        assertTrue(World.contains(n, -1,1,0));
        assertTrue(World.contains(n, -1,1,1));

        assertTrue(World.contains(n, 0,-1,-1));
        assertTrue(World.contains(n, 0,-1,0));
        assertTrue(World.contains(n, 0,-1,1));
        assertTrue(World.contains(n, 0,0,-1));
        assertTrue(World.contains(n, 0,0,1));
        assertTrue(World.contains(n, 0,1,-1));
        assertTrue(World.contains(n, 0,1,0));
        assertTrue(World.contains(n, 0,1,1));

        assertTrue(World.contains(n, 1,-1,-1));
        assertTrue(World.contains(n, 1,-1,0));
        assertTrue(World.contains(n, 1,-1,1));
        assertTrue(World.contains(n, 1,0,-1));
        assertTrue(World.contains(n, 1,0,0));
        assertTrue(World.contains(n, 1,0,1));
        assertTrue(World.contains(n, 1,1,-1));
        assertTrue(World.contains(n, 1,1,0));
        assertTrue(World.contains(n, 1,1,1));


        assertFalse(FiniteWorld.contains(n, 0, 0, 0));

        assertEquals(Math.pow(3, 3)-1, n.size());
    }

    @Test
    public void coordinateStringTest() {
        assertEquals("[0, 1, 2, 3]", FiniteWorld.coordinateString(0, 1, 2, 3));
        assertEquals("null", FiniteWorld.coordinateString(null));
    }

    @Test
    public void validateCoordinatesEqual() {
        World.validateCoordinatesEqual(null, null);
        World.validateCoordinatesEqual(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        World.validateCoordinatesEqual(new int[]{}, new int[]{});
    }

    @Test
    public void validateCoordinatesEqual_leftNull() {
        try {
            World.validateCoordinatesEqual(null, new int[]{1, 2, 3});
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            assertEquals("left coordinate is null but right coordinate is not null.", e.getMessage());
        }
    }

    @Test
    public void validateCoordinatesEqual_rightNull() {
        try {
            World.validateCoordinatesEqual(new int[]{1, 2, 3}, null);
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            assertEquals("left coordinate is not null but right coordinate is null.", e.getMessage());
        }
    }

    @Test
    public void validateCoordinatesEqual_differentNumberOfDimensions() {
        final int[] left = new int[]{1, 2};
        final int[] right = new int[]{1, 2, 3};
        try {

            World.validateCoordinatesEqual(left, right);
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            final String expectedMessage = "The number of dimensions for each coordinate is different. (" + left.length + " != " + right.length + ")";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateCoordinatesEqual_differentNumberOfDimensions_0() {
        final int[] left = new int[]{0, 2, 3};
        final int[] right = new int[]{1, 2, 3};
        try {

            World.validateCoordinatesEqual(left, right);
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            final String expectedMessage = "Dimension [" + 0 + "] is not the same! (" + 0 + " != " + 1 + ")";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateCoordinatesEqual_differentNumberOfDimensions_1() {
        final int[] left = new int[]{1, 0, 3};
        final int[] right = new int[]{1, 2, 3};
        try {

            World.validateCoordinatesEqual(left, right);
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            final String expectedMessage = "Dimension [" + 1 + "] is not the same! (" + 0 + " != " + 2 + ")";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void validateCoordinatesEqual_differentNumberOfDimensions_2() {
        final int[] left = new int[]{1, 2, 0};
        final int[] right = new int[]{1, 2, 3};
        try {

            World.validateCoordinatesEqual(left, right);
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            final String expectedMessage = "Dimension [" + 2 + "] is not the same! (" + 0 + " != " + 3 + ")";
            assertEquals(expectedMessage, e.getMessage());
        }
    }



}

package mepco.ca.world;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static mepco.ca.world.FiniteWorld.coordinateString;
import static org.junit.jupiter.api.Assertions.*;

public class InfiniteWorldTest {

    public static final String DEFAULT = " ";
    private InfiniteWorld<String> world;

    @BeforeEach
    public void onSetup() {
        world = new InfiniteWorld<>(DEFAULT, 4);
    }

    @AfterEach
    public void onTearDown() {

    }

    @Test
    public void testSetup() {

    }

    @Test
    public void calcSize() {
        assertEquals(125, FiniteWorld.calcSize(5, 5, 5));
        assertEquals((2*3*13), FiniteWorld.calcSize(2, 3, 13));
    }

    @Test
    public void getDimensions() {
        final Dimension[] dim = world.getDimensions();

        assertEquals(4, world.getNumDimensions());
        assertFalse(dim[0].isFinite());
        assertFalse(dim[1].isFinite());
        assertFalse(dim[2].isFinite());
        assertFalse(dim[3].isFinite());
    }

    @Test
    public void getMin() {
        assertNull(world.getMin(0));
        assertNull(world.getMin(1));
        assertNull(world.getMin(2));
    }

    @Test
    public void getMax() {
        assertNull(world.getMax(0));
        assertNull(world.getMax(1));
        assertNull(world.getMax(2));
    }

    @Test
    public void getSize() {
        assertNull(world.getSize(0));
        assertNull(world.getSize(1));
        assertNull(world.getSize(2));
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
        final int dimension = 999;
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
        final int[] coordinate = new int[]{0, 0, 0, 0, 0, 0, 0};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "A coordinate must specify exactly " + world.getNumDimensions() + " dimensions.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void validateDimensions_cantBeNull() {
        try {
            world.validateDimensions(null);
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("The dimensions array can not be null.", e.getMessage());
        }
    }


    @Test
    public void validateDimensions_cantBeEmpty() {
        try {
            world.validateDimensions(new InfiniteDimension[]{});
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("There must be at least one dimension.", e.getMessage());
        }
    }



    @Test
    public void getAdjacent() {
        final List<int[]> adj = world.getAdjacent(1, 1, 1, 1);

        adj.stream().forEach(c -> {
            System.out.println(coordinateString(c));
        });

        assertTrue(FiniteWorld.contains(adj, 0, 1, 1, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 0, 1, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 2, 1, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 1, 0, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 1, 2, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 1, 1, 0));
        assertTrue(FiniteWorld.contains(adj, 1, 1, 1, 2));

        assertFalse(FiniteWorld.contains(adj, 0, 0, 0, 0));



    }

    @Test
    public void coordinateStringTest() {
        assertEquals("[0, 1, 2, 3]", FiniteWorld.coordinateString(0, 1, 2, 3));
        assertEquals("null", FiniteWorld.coordinateString(null));
    }

    @Test
    public void validateCoordinatesEqual() {
        FiniteWorld.validateCoordinatesEqual(null, null);
        FiniteWorld.validateCoordinatesEqual(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        FiniteWorld.validateCoordinatesEqual(new int[]{}, new int[]{});
    }

    @Test
    public void validateCoordinatesEqual_leftNull() {
        try {
            FiniteWorld.validateCoordinatesEqual(null, new int[]{1, 2, 3});
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            assertEquals("left coordinate is null but right coordinate is not null.", e.getMessage());
        }
    }

    @Test
    public void validateCoordinatesEqual_rightNull() {
        try {
            FiniteWorld.validateCoordinatesEqual(new int[]{1, 2, 3}, null);
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

            FiniteWorld.validateCoordinatesEqual(left, right);
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

            FiniteWorld.validateCoordinatesEqual(left, right);
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

            FiniteWorld.validateCoordinatesEqual(left, right);
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

            FiniteWorld.validateCoordinatesEqual(left, right);
            fail("Expected exception to be thrown.");
        } catch (Exception e) {
            final String expectedMessage = "Dimension [" + 2 + "] is not the same! (" + 0 + " != " + 3 + ")";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

}

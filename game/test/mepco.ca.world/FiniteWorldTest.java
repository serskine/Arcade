package mepco.ca.world;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static mepco.ca.world.FiniteWorld.coordinateString;
import static mepco.ca.world.World.buildDimensions;
import static org.junit.jupiter.api.Assertions.*;

public class FiniteWorldTest {

    public static final String DEFAULT = " ";
    private FiniteWorld<String> world;

    @BeforeEach
    public void onSetup() {
        world = new FiniteWorld(DEFAULT, 2, 3, 4);
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
        final int[] dim = FiniteWorld.extractFiniteDimensionSizes((FiniteDimension[]) world.getDimensions());

        assertEquals(3, world.getNumDimensions());

        assertEquals(3, dim.length);
        assertEquals(2, dim[0]);
        assertEquals(3, dim[1]);
        assertEquals(4, dim[2]);
    }

    @Test
    public void getMin() {
        assertEquals(0, world.getMin(0));
        assertEquals(0, world.getMin(1));
        assertEquals(0, world.getMin(2));
    }

    @Test
    public void getMax() {
        assertEquals(1, world.getMax(0));
        assertEquals(2, world.getMax(1));
        assertEquals(3, world.getMax(2));
    }

    @Test
    public void getSize() {
        assertEquals(2, world.getSize(0));
        assertEquals(3, world.getSize(1));
        assertEquals(4, world.getSize(2));
    }

    @Test
    public void getDimOffset() {
        int d2 = world.getDimensionMod(2);
        int d1 = world.getDimensionMod(1);
        int d0 = world.getDimensionMod(0);

        assertEquals(1, d2);
        assertEquals(4, d1);
        assertEquals(12, d0);
    }

    @Test
    public void toIndex() {
        assertEquals(0, world.toIndex(0, 0, 0));
        assertEquals(1, world.toIndex(0, 0, 1));
        assertEquals(2, world.toIndex(0, 0, 2));
        assertEquals(3, world.toIndex(0, 0, 3));
        assertEquals(4, world.toIndex(0, 1, 0));
        assertEquals(5, world.toIndex(0, 1, 1));
        assertEquals(6, world.toIndex(0, 1, 2));
        assertEquals(7, world.toIndex(0, 1, 3));
        assertEquals(8, world.toIndex(0, 2, 0));
        assertEquals(9, world.toIndex(0, 2, 1));
        assertEquals(10, world.toIndex(0, 2, 2));
        assertEquals(11, world.toIndex(0, 2, 3));

        assertEquals(12, world.toIndex(1, 0, 0));
        assertEquals(13, world.toIndex(1, 0, 1));
        assertEquals(14, world.toIndex(1, 0, 2));
        assertEquals(15, world.toIndex(1, 0, 3));
        assertEquals(16, world.toIndex(1, 1, 0));
        assertEquals(17, world.toIndex(1, 1, 1));
        assertEquals(18, world.toIndex(1, 1, 2));
        assertEquals(19, world.toIndex(1, 1, 3));
        assertEquals(20, world.toIndex(1, 2, 0));
        assertEquals(21, world.toIndex(1, 2, 1));
        assertEquals(22, world.toIndex(1, 2, 2));
        assertEquals(23, world.toIndex(1, 2, 3));
    }

    @Test
    public void toCoordinate() {

        for(int i=0; i<24; i++) {
            int[] c = world.toCoordinate(i);
            System.out.println("world.toCoordinate(" + i + ") = " + coordinateString(c));
        }
        assertArrayEquals(new int[]{0, 0, 0}, world.toCoordinate(0));
        assertArrayEquals(new int[]{0, 0, 1}, world.toCoordinate(1));
        assertArrayEquals(new int[]{0, 0, 2}, world.toCoordinate(2));
        assertArrayEquals(new int[]{0, 0, 3}, world.toCoordinate(3));
        assertArrayEquals(new int[]{0, 1, 0}, world.toCoordinate(4));
        assertArrayEquals(new int[]{0, 1, 1}, world.toCoordinate(5));
        assertArrayEquals(new int[]{0, 1, 2}, world.toCoordinate(6));
        assertArrayEquals(new int[]{0, 1, 3}, world.toCoordinate(7));
        assertArrayEquals(new int[]{0, 2, 0}, world.toCoordinate(8));
        assertArrayEquals(new int[]{0, 2, 1}, world.toCoordinate(9));
        assertArrayEquals(new int[]{0, 2, 2}, world.toCoordinate(10));
        assertArrayEquals(new int[]{0, 2, 3}, world.toCoordinate(11));
        assertArrayEquals(new int[]{1, 0, 0}, world.toCoordinate(12));
        assertArrayEquals(new int[]{1, 0, 1}, world.toCoordinate(13));
        assertArrayEquals(new int[]{1, 0, 2}, world.toCoordinate(14));
        assertArrayEquals(new int[]{1, 0, 3}, world.toCoordinate(15));
        assertArrayEquals(new int[]{1, 1, 0}, world.toCoordinate(16));
        assertArrayEquals(new int[]{1, 1, 1}, world.toCoordinate(17));
        assertArrayEquals(new int[]{1, 1, 2}, world.toCoordinate(18));
        assertArrayEquals(new int[]{1, 1, 3}, world.toCoordinate(19));
        assertArrayEquals(new int[]{1, 2, 0}, world.toCoordinate(20));
        assertArrayEquals(new int[]{1, 2, 1}, world.toCoordinate(21));
        assertArrayEquals(new int[]{1, 2, 2}, world.toCoordinate(22));
        assertArrayEquals(new int[]{1, 2, 3}, world.toCoordinate(23));
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
    public void getCoordinateItr() {
        final Iterator<int[]> itr = world.getCoordinateItr();

        int count = 0;
        while(itr.hasNext()) {
            int[] c = itr.next();
            System.out.println(coordinateString(c));
            count++;
        }

        assertEquals(24, count);
    }

    @Test
    public void getCoordinateStream() {

        System.out.println("stream [ ?, ?, ?]");
        world.getCoordinateStream().forEach(c -> System.out.println(coordinateString(c)));

        System.out.println("stream [ ?, 0, ?]");
        world.getCoordinateStream().filter(c -> c[1] == 0).forEach(c -> System.out.println(coordinateString(c)));
    }

    @Test
    public void getLocationsStream() {
        world.getLocationsStream().forEach(l -> l.set("X"));
        world.getLocationsStream().forEach(l -> {
            final StringBuilder sb = new StringBuilder();
            sb.append("______________________________");
            sb.append("\nvalue      : " + l.getValue());
            sb.append("\ncoordinate : " + coordinateString(l.getCoordinate()));
            sb.append("\n______________________________\n");
            System.out.println(sb.toString());
        });
    }

    @Test
    public void locationByIndexSameAsLocationByCoordinate() {
        final int[] coordinate = new int[] {1, 2, 3};
        final int index = world.toIndex(coordinate);

        Location<String> byIndex = world.locationByIndex(index);
        Location<String> byCoordinate = world.location(coordinate);

        assertArrayEquals(coordinate, byIndex.getCoordinate());

        assertEquals(coordinate, byCoordinate.getCoordinate());

        assertEquals(byIndex.getValue(), byCoordinate.getValue());
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
    public void validateIndexToSmall() {
        final int index = -1;
        try {
            world.validateIndex(index);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "The index is < 0";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateIndexToLarge() {
        final int index = 24;
        try {
            world.validateIndex(index);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "The index is >= " + 24;
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void validateCoordinate_0_toSmall() {
        final int[] coordinate = new int[]{-1, 0, 0};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Invalid coordinate dimension [" + 0 + "] : " + -1 + " < " + 0 + ". Below minimum.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateCoordinate_1_toSmall() {
        final int[] coordinate = new int[]{0, -1, 0};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Invalid coordinate dimension [" + 1 + "] : " + -1 + " < " + 0 + ". Below minimum.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateCoordinate_2_toSmall() {
        final int[] coordinate = new int[]{0, 0, -1};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Invalid coordinate dimension [" + 2 + "] : " + -1 + " < " + 0 + ". Below minimum.";
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
    public void validateCoordinate_0_toLarge() {
        final int[] coordinate = new int[]{2, 0, 0};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Invalid coordinate dimension [" + 0 + "] : " + 2 + " > " + 1 + ". Above maximum.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateCoordinate_1_toLarge() {
        final int[] coordinate = new int[]{0, 3, 0};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Invalid coordinate dimension [" + 1 + "] : " + 3 + " > " + 2 + ". Above maximum.";
            assertEquals(expectedMessage, e.getMessage());
        }
    }


    @Test
    public void validateCoordinate_2_toLarge() {
        final int[] coordinate = new int[]{0, 0, 4};
        try {
            world.validateCoordinate(coordinate);
            fail("Expected exception");
        } catch (Exception e) {
            final String expectedMessage = "Invalid coordinate dimension [" + 2 + "] : " + 4 + " > " + 3 + ". Above maximum.";
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
            world.validateDimensions(buildDimensions());
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("There must be at least one dimension.", e.getMessage());
        }
    }

    @Test
    public void validateDimensions_invalidDimensionSize_0() {
        try {
            world.validateDimensions(buildDimensions(0, 3, 4));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("Dimension " + 0 + " must have a size of at least 1", e.getMessage());
        }
    }


    @Test
    public void validateDimensions_invalidDimensionSize_1() {
        try {
            world.validateDimensions(buildDimensions(2, 0, 4));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("Dimension " + 1 + " must have a size of at least 1", e.getMessage());
        }
    }


    @Test
    public void validateDimensions_invalidDimensionSize_2() {
        try {
            world.validateDimensions(buildDimensions(2, 3, 0));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals("Dimension " + 2 + " must have a size of at least 1", e.getMessage());
        }
    }

    @Test
    public void getAdjacent() {
        final List<int[]> adj = world.getAdjacent(1, 1, 1);

        adj.stream().forEach(c -> {
            System.out.println(coordinateString(c));
        });

        assertTrue(FiniteWorld.contains(adj, 0, 1, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 0, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 2, 1));
        assertTrue(FiniteWorld.contains(adj, 1, 1, 0));
        assertTrue(FiniteWorld.contains(adj, 1, 1, 2));

        assertFalse(FiniteWorld.contains(adj, 0, 0, 0));



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

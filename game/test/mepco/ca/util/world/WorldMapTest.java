package mepco.ca.util.world;

import mepco.ca.games.conway.WorldMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorldMapTest {

    public static final String DEFAULT = " ";
    private WorldMap  world;
    private List<int[]> neighbours;

    @BeforeEach
    public void onSetup() {
        world = new WorldMap(3, 3);
    }

    @AfterEach
    public void onTearDown() {
        if (neighbours==null) {
            System.out.println("neighbours is null");
        } else {
            for (int[] c : neighbours) {
                System.out.println(" - " + WorldMap.coordinateString(c));
            }
        }
    }

    @Test
    public void testSetup() {
        assertNotNull(world);
    }

    @Test
    public void getNeighbours_noWall() {
        neighbours = world.getNeighbours(new int[] {1, 1});

        assertEquals(8, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{0,0}));
        assertTrue(World.contains(neighbours, new int[]{0,1}));
        assertTrue(World.contains(neighbours, new int[]{0,2}));
        assertTrue(World.contains(neighbours, new int[]{2,0}));
        assertTrue(World.contains(neighbours, new int[]{2,1}));
        assertTrue(World.contains(neighbours, new int[]{2,2}));
        assertTrue(World.contains(neighbours, new int[]{1,0}));
        assertTrue(World.contains(neighbours, new int[]{1,2}));
    }

    @Test
    public void getNeighbours_SW() {
        neighbours = world.getNeighbours(new int[] {0, 0});

        assertEquals(3, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{0,1}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{1,0}));
    }


    @Test
    public void getNeighbours_NE() {
        neighbours = world.getNeighbours(new int[] {2, 2});

        assertEquals(3, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{1,2}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{2,1}));
    }


    @Test
    public void getNeighbours_NW() {
        neighbours = world.getNeighbours(new int[] {0, 2});

        assertEquals(3, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{1,2}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{0,1}));
    }


    @Test
    public void getNeighbours_SE() {
        neighbours = world.getNeighbours(new int[] {2, 0});

        assertEquals(3, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{2,1}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{1,0}));
    }


    @Test
    public void getNeighbours_N() {
        neighbours = world.getNeighbours(new int[] {1, 2});

        assertEquals(5, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{0,2}));
        assertTrue(World.contains(neighbours, new int[]{0,1}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{2,1}));
        assertTrue(World.contains(neighbours, new int[]{2,2}));
    }


    @Test
    public void getNeighbours_S() {
        neighbours = world.getNeighbours(new int[] {1, 0});

        assertEquals(5, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{0,0}));
        assertTrue(World.contains(neighbours, new int[]{0,1}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{2,1}));
        assertTrue(World.contains(neighbours, new int[]{2,0}));
    }


    @Test
    public void getNeighbours_E() {
        neighbours = world.getNeighbours(new int[] {2, 1});

        assertEquals(5, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{1,0}));
        assertTrue(World.contains(neighbours, new int[]{2,0}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{1,2}));
        assertTrue(World.contains(neighbours, new int[]{2,2}));
    }


    @Test
    public void getNeighbours_W() {
        neighbours = world.getNeighbours(new int[] {0, 1});

        assertEquals(5, neighbours.size());

        assertTrue(World.contains(neighbours, new int[]{0,0}));
        assertTrue(World.contains(neighbours, new int[]{1,0}));
        assertTrue(World.contains(neighbours, new int[]{1,1}));
        assertTrue(World.contains(neighbours, new int[]{1,2}));
        assertTrue(World.contains(neighbours, new int[]{0,2}));
    }
}

package mepco.ca;

import mepco.ca.world.FiniteWorld;
import mepco.ca.world.InfiniteWorld;
import mepco.ca.world.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldMap extends FiniteWorld<Tile> {
    private int width;
    private int height;
    private boolean isPaused;

    public WorldMap(int width, int height) {
        super(Tile.DEAD, width, height);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

//    public Stream<Location<Tile>> getLocationsStream() {
//        final List<Location<Tile>> locations = new ArrayList<>();
//        for(int x=0; x<getWidth(); x++) {
//            for(int y=0; y<getHeight(); y++) {
//                final int[] coord = new int[] {x, y};
//                final Location<Tile> location = this.location(coord);
//                locations.add(location);
//            }
//        }
//        return locations.stream();
//    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }


}

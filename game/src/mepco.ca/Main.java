package mepco.ca;

import mepco.ca.world.Location;
import mepco.ca.world.World;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main implements Runnable {

    static Main INSTANCE = new Main();

    public static void main(String[] args) {
        INSTANCE.run();
    }

    private WorldMap map;
    private MapView mapView;
    private MapController mapController;
    private JFrame frame;
    private int delay = 100;

    private Map<Integer, Boolean> rules = new HashMap<>();

    @Override
    public void run() {

        createSeedMap();
        this.mapView = new MapView();
        createFrameAndViewAndController(map);

        boolean done = false;
        while(!done) {
            try {
                Thread.sleep(delay);
                onTurn();
            } catch(Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
            }
        }
    }

    void createSeedMap() {
        final double probLive = 0.5D;


        this.map = new WorldMap(50, 50);
        this.map.setPaused(false);
    }

    void createFrameAndViewAndController(WorldMap map) {
        final int cellSize = Math.min(1000 / map.getWidth(), 1000/map.getHeight());

        this.mapView = new MapView();
        this.mapView.setCellSize(cellSize);
        this.mapView.setMap(map);

        this.mapController = new MapController();
        this.mapController.setMapView(mapView);

        frame = new JFrame("Character Sim");
        frame.setSize(mapView.getWidth(), mapView.getHeight());

        frame.setLocation(0, 0);
        frame.add(this.mapView);
        frame.invalidate();
        frame.setVisible(true);
    }

    public void onTurn() {
        final World<Tile> currentState = map.copy();

        if (!map.isPaused()) {
            map.getLocationsStream().forEach(location -> this.processLocation(location, currentState));
        }

        frame.repaint();
    }

    public void processLocation(final Location<Tile> location, final World<Tile> currentState) {
        final int[] p = location.getCoordinate();
        // GAME OF LIFE
        final List<int[]> neighbours = map.getNeighbours(p);
        final Tile currentValue = location.getValue();

        final Map<Tile, Integer> countMap = counts(location, currentState);
        int deadCount = countMap.getOrDefault(Tile.DEAD, 0);
        int healthyCount = countMap.getOrDefault(Tile.HEALTHY, 0);
        int bloodiedCount = countMap.getOrDefault(Tile.BLOODIED, 0);
        int hurtCount = countMap.getOrDefault(Tile.HURT, 0);

        int goodCount = healthyCount + hurtCount;
        int badCount = deadCount + bloodiedCount;

        final Tile weakerValue = weaken(currentValue);
        final Tile strongerValue = strengthen(currentValue);

        final boolean isLive = currentValue == Tile.HEALTHY || currentValue == Tile.HURT;

        if (isLive) {
            if (goodCount < 2 || goodCount > 3) {
                location.set(weakerValue);
            } else {
                location.set(strongerValue);
            }
        } else {
            if (goodCount == 3) {
                location.set(strongerValue);
            } else {
                location.set(weakerValue);
            }
        }

    }

    private boolean isLive(Tile tile) {
        return (tile==Tile.HEALTHY || tile==Tile.BLOODIED);
    }

    private Tile weaken(Tile prevValue) {
//        switch(prevValue) {
//            case DEAD -> {
//                return Tile.DEAD;
//            }
//            case BLOODIED -> {
//                return Tile.DEAD;
//            }
//            case HURT -> {
//                return Tile.BLOODIED;
//            }
//            case HEALTHY -> {
//                return Tile.HURT;
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + prevValue);
//        }
        return Tile.DEAD;
    }

    private Tile strengthen(Tile prevValue) {
//        switch(prevValue) {
//            case DEAD -> {
//                return Tile.BLOODIED;
//            }
//            case BLOODIED -> {
//                return Tile.HURT;
//            }
//            case HURT -> {
//                return Tile.HEALTHY;
//            }
//            case HEALTHY -> {
//                return Tile.HEALTHY;
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + prevValue);
//        }
        return Tile.HEALTHY;
    }

    /**
     * Retrieve the number of each tile that is adjacent to the current tile.
     * @param location
     * @param currentState
     * @return
     */
    Map<Tile, Integer> counts(final Location<Tile> location, final World<Tile> currentState) {
        final Map<Tile, Integer> counts = new HashMap<>();
        final int[] p = location.getCoordinate();
        final List<int[]> neighbours = map.getNeighbours(p);
        neighbours.stream()
                .map(n -> currentState.location(n).getValue())
                .forEach(t -> {
                    counts.put(t, counts.getOrDefault(t, 0) + 1);
                });
        return counts;
    }

    private boolean isBad(Tile tile) {
        return  (   Tile.DEAD.equals(tile)
                ||  Tile.BLOODIED.equals(tile)
        );
    }

    public Map<Integer, Boolean> getRules() {
        return rules;
    }

    public void setRules(Map<Integer, Boolean> rules) {
        this.rules = rules;
    }
}

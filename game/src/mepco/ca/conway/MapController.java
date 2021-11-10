package mepco.ca.conway;

import mepco.ca.util.world.FiniteWorld;
import mepco.ca.util.world.Location;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MapController extends Controller {

    private MapView mapView;
    private Set<Point> selectedScreenPoints = new HashSet<>();

    private void paintAtScreenLocation() {
        final int sx = location.x;
        final int sy = location.y;

        System.out.println("Screen clicked at [" + sx + ", " + sy + "]");

        if (mapView != null) {
            final WorldMap map = mapView.getMap();

            final Optional<int[]> coordOpt = mapView.screenToCoordinate(sx, sy);
            if (coordOpt.isPresent()) {
                final int[] c = coordOpt.get();
                System.out.println("Moused released at " + FiniteWorld.coordinateString(c));
                if (map != null) {
                    Location<Tile> l = map.location(c);
                    final Tile oldValue = l.getValue();
                    final Tile newValue = getPaintValue();
                    System.out.println("Toggling " + FiniteWorld.coordinateString(c) + " from " + oldValue + " -> " + newValue);
                    l.set(newValue);
                }
            }
            mapView.repaint();
        }
    }

    public void paintSelected() {
        final WorldMap map = mapView.getMap();
        if (map != null) {
            for (Point p : selectedScreenPoints) {
                final Optional<int[]> coordOpt = mapView.screenToCoordinate(p.x, p.y);
                if (coordOpt.isPresent()) {
                    final int[] c = coordOpt.get();
                    Location<Tile> l = map.location(c);
                    final Tile oldValue = l.getValue();
                    final Tile newValue = getPaintValue();
                    System.out.println("Toggling " + FiniteWorld.coordinateString(c) + " from " + oldValue + " -> " + newValue);
                    l.set(newValue);
                }
            }
            mapView.repaint();
        }
    }


    public void setMapView(MapView mapView) {
        if (this.mapView != null) {
            this.mapView.removeMouseListener(this);
            this.mapView.removeMouseMotionListener(this);
            this.mapView.removeMouseWheelListener(this);
        }
        this.mapView = mapView;
        this.mapView.addMouseListener(this);
        this.mapView.addMouseMotionListener(this);
        this.mapView.addMouseWheelListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseMoved(e);
        final WorldMap worldMap = mapView.getMap();
        if (isRightDown) {
            System.out.println("map paused = true");
            worldMap.setPaused(true);
        } else {
            System.out.println("map paused = false");
            worldMap.setPaused(false);
        }

        if (isLeftDown) {
            selectedScreenPoints.add(e.getPoint());
        }


        paintSelected();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        final WorldMap worldMap = mapView.getMap();
        if (isRightDown) {
            System.out.println("map paused = true");
            worldMap.setPaused(true);
        }

        if (isLeftDown) {
            selectedScreenPoints.add(e.getPoint());
        }

        if (isWheelDown) {
            worldMap.getLocationsStream().forEach(location ->
                    location.set(Tile.DEAD)
            );
        }

        paintSelected();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        final WorldMap worldMap = mapView.getMap();
        if (!isRightDown) {
            System.out.println("map paused = false");
            worldMap.setPaused(false);
        }
        if (!isLeftDown) {
            paintSelected();
            selectedScreenPoints.clear();
        }
    }

    protected Tile getPaintValue() {

        while(scrollWheelPosition < 0) {
            scrollWheelPosition += Tile.values().length;
        }
        scrollWheelPosition = scrollWheelPosition % Tile.values().length;
        return Tile.values()[scrollWheelPosition];
    }
}

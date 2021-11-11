package mepco.ca.games.conway;

import java.awt.*;
import java.util.Optional;

public class MapView extends View {

    private int cellSize = 50;
    private WorldMap map;
    private MapController controller;

    public void MapView() {
        controller = new MapController();
        controller.setMapView(this);
        this.addMouseListener(controller);
    }

    @Override
    public void renderSelf(Graphics g) {
        map.getLocationsStream().forEach(p -> {
            int[] coordinate = p.getCoordinate();
            Tile v = p.getValue();
            renderCell(g, coordinate[0], coordinate[1], v);
        });


        renderStatus(g,  getCellSize()/2, getCellSize()/2, map.isPaused());
    }

    void renderCell(Graphics g, int x, int y, Tile value) {
         final Rectangle r = getCellBounds(x, y);
         final Color fgColor = value.fgColor;
         final Color bgColor = value.bgColor;
         final Color oldColor = g.getColor();
         g.setColor(bgColor);
         g.fillRect(r.x, r.y, r.width, r.height);
         g.setColor(fgColor);
         g.drawRect(r.x, r.y, r.width, r.height);
         g.setColor(oldColor);
    }

    void renderStatus(Graphics g, int x, int y, boolean isPaused) {
        Color oldColor = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString((isPaused ? "PAUSED" : "RUNNING"), x, y);
        g.setColor(oldColor);
    }


    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = Math.max(1, cellSize);
        checkSize();
    }

    Rectangle getCellBounds(int x, int y) {
        final Rectangle r = new Rectangle();

        r.x = getX() + x * cellSize;
        r.y = getX() + y * cellSize;
        r.width = cellSize;
        r.height = cellSize;

        return r;
    }

    public WorldMap getMap() {
        return map;
    }

    public void setMap(WorldMap map) {
        this.map = map;
        checkSize();
    }

    void checkSize() {
        if (getMap() != null) {
            setSize(
                this.cellSize * map.getWidth(),
                this.cellSize * map.getHeight()
            );
            invalidate();
        }

    }

    Optional<int[]> screenToCoordinate(int sx, int sy) {
        if (map==null) {
            return Optional.empty();
        } else {

            int x = sx / getCellSize();
            int y = sy / getCellSize();
            int[] c = new int[]{x, y};
            if (map.isValidCoordinate(c)) {
                return Optional.of(c);
            } else {
                return Optional.empty();
            }
        }
    }



}

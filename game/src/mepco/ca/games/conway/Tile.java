package mepco.ca.games.conway;

import java.awt.*;

public enum Tile {
        DEAD(Color.DARK_GRAY),
        BLOODIED(Color.RED),
        HURT(Color.YELLOW),
        HEALTHY(Color.GREEN)
//        BLACK(Color.BLACK),
//        RED(Color.RED),
//        GREEN(Color.GREEN),
//        BLUE(Color.BLUE),
//        MAGENTA(Color.MAGENTA),
//        ORANGE(Color.ORANGE),
//        PINK(Color.PINK),
//        YELLOW(Color.YELLOW),
        ;

        private Tile(Color bgColor) {
            this.bgColor = bgColor;
        }

        public final Color bgColor;
        public final Color fgColor = Color.BLACK;

        public static final int find(Tile expected) {
                for (int i = 0; i < Tile.values().length; i++) {
                        if (Tile.values()[i].equals(expected)) {
                                return i;
                        }
                }
                throw new RuntimeException("Unexpected value [" + expected + "]");
        }
}

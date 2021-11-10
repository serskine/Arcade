package mepco.ca.util.draw;

import java.awt.*;

public class Draw {

    public static final void drawText(Graphics2D g, final Point p, final int height, final String text) {
        renderRetroText(g, p, height, g.getColor(), null, text);
    }

    public static final void fillText(Graphics2D g, final Point p, final int height, final String text) {
        renderRetroText(g, p, height, null, g.getColor(), text);
    }

    public static final Dimension getTextSize(Graphics2D g, final int height, final String text) {
        final Rectangle r = renderRetroText(g, new Point(0,0), height, null, null, text);
        return r.getSize();
    }

    public static final Rectangle renderRetroText(
            final Graphics2D g,
            final Point p,
            final int height,
            final Color lineColor,  // If null then the lines are not drawn
            final Color fillColor,   // If null then the fill is not painted
            final String text
    ) {

        Color prevColor = null;

        if (g!=null) {
            prevColor = g.getColor();
        }

        int textWidth = 0;
        int textHeight = 0;

        if (text != null && text.length()>0) {
            final int unit = height / 5;

            boolean[][] flags = RetroChar.getFlagsOf(text, RetroChar.SPACE);
            int maxRowW = 0;

            for(int r=0; r<flags.length; r++) {
                final boolean[] row = flags[r];
                int rowW = 0;
                for(int c=0; c<row.length; c++) {
                    rowW += unit;
                    if (g!=null && row[c]==true) {
                        final int pixelX = p.x + c * unit;
                        final int pixelY = p.y + r * unit;

                        if (g != null && fillColor != null) {
                            g.setColor(fillColor);
                            g.fillRect(pixelX, pixelY, unit, unit);
                        }
                        if (g != null && lineColor != null) {
                            g.setColor(lineColor);
                            boolean drawNorth = (r==0               || flags[r-1][c]==false);
                            boolean drawSouth = (r==flags.length-1  || flags[r+1][c]==false);
                            boolean drawEast =  (c==row.length-1    || flags[r][c+1]==false);
                            boolean drawWest =  (c==0               || flags[r][c-1]==false);
                            drawRect(g, pixelX, pixelY, unit, unit, drawNorth, drawEast, drawSouth, drawWest);
                        }
                    }
                }
                maxRowW = Math.max(maxRowW, rowW);
            }

            textHeight = flags.length * unit;
            textWidth = maxRowW;
        }

        final Rectangle rect = new Rectangle(p.x, p.y, textWidth, textHeight);
        if (g!=null) {
            g.setColor(prevColor);
        }

        // This will return the rectangle area that contains the text on the screen.
        return rect;
    }

    public static final void drawRect(
        final Graphics g,
        final int x1,
        final int y1,
        final int width,
        final int height,
        final boolean renderNorth,
        final boolean renderEast,
        final boolean renderSouth,
        final boolean renderWest
    ) {
        final int x2 = x1 + width;
        final int y2 = y1 + height;

        if (g!=null) {
            if (renderNorth)    {   g.drawLine(x1, y1, x2, y1); }
            if (renderEast)     {   g.drawLine(x2, y1, x2, y2); }
            if (renderSouth)    {   g.drawLine(x2, y2, x1, y2); }
            if (renderWest)     {   g.drawLine(x1, y2, x1, y1); }
        }
    }


}

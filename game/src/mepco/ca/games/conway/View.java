package mepco.ca.games.conway;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JComponent {

    @Override
    public void paint(Graphics g) {
        try {
            renderSelf(g);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            int x1 = getX();
            int x2 = getX() + getWidth();
            int y1 = getY();
            int y2 = getY() + getHeight();
            g.drawLine(x1, y1, x2, y1);
            g.drawLine(x1, y2, x2, y1);
            g.drawLine(x1, y1, x1, y2);
            g.drawLine(x1, y2, x2, y2);
            g.drawLine(x2, y1, x2, y2);
        }
    }

    public abstract void renderSelf(Graphics g);

}

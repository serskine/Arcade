package mepco.ca;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller implements MouseInputListener, MouseWheelListener {

    protected boolean isLeftDown = false;
    protected boolean isRightDown = false;
    protected boolean isWheelDown = false;
    protected Point location = new Point(0,0);
    protected int scrollWheelPosition = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked [" + e.getX() + ", " + e.getY() + "]");
        location = e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed [" + e.getX() + ", " + e.getY() + "] -> " + e.getButton());

        switch (e.getButton()) {
            case (MouseEvent.BUTTON1) -> {
                isLeftDown = true;
            }
            case (MouseEvent.BUTTON3) -> {
                isRightDown = true;
            }
            case (MouseEvent.BUTTON2) -> {
                isWheelDown = true;
            }
        }
        location = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased [" + e.getX() + ", " + e.getY() + "] -> " +
                "Button1[" + MouseEvent.BUTTON1 + "]" +
                "Button2[" + MouseEvent.BUTTON2 + "]" +
                "Button3[" + MouseEvent.BUTTON3 + "]"
        );
        switch(e.getButton()) {
            case(MouseEvent.BUTTON1) -> {
                isLeftDown = false;
            }
            case(MouseEvent.BUTTON3) -> {
                isRightDown = false;
            }
            case(MouseEvent.BUTTON2) -> {
                isWheelDown = false;
            }
        }
        location = e.getPoint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("mouseEntered [" + e.getX() + ", " + e.getY() + "]");
        location = e.getPoint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("mouseExited [" + e.getX() + ", " + e.getY() + "]");
        location = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.println("mouseDragged [" + e.getX() + ", " + e.getY() + "]");
        location = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println("mouseMoved [" + e.getX() + ", " + e.getY() + "]");
        location = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
//        System.out.println("mouseWheelMoved [" + e.getX() + ", " + e.getY() + "] - Wheel " + e.getWheelRotation());
        scrollWheelPosition += e.getWheelRotation();
    }
}

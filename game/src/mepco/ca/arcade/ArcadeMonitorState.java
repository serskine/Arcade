package mepco.ca.arcade;

import java.awt.*;

public class ArcadeMonitorState {
    private int width;
    private int height;
    private boolean powered;
    private Color backgroundColor;
    private OrientationState orientationState;

    public ArcadeMonitorState() {
        update(null);
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = Math.max(0, width);
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = Math.max(0, height);
    }

    public boolean isPowered() {
        return this.powered;
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    public void update(Component component) {
        if (component==null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.width = screenSize.width;
            this.height = screenSize.height;
            this.powered = false;
            this.orientationState = OrientationState.NORTH;
            this.backgroundColor = Color.BLACK;
        } else {
            final Graphics2D g = (Graphics2D) component.getGraphics();

            if (g!=null) {
                this.width = component.getWidth();
                this.height = component.getHeight();
                this.powered = component.isShowing();
                this.backgroundColor = g.getBackground();
            }

        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public OrientationState getOrientationState() {
        return orientationState;
    }

    public void setOrientationState(OrientationState orientationState) {
        this.orientationState = orientationState;
    }
}

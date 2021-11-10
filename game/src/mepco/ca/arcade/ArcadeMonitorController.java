package mepco.ca.arcade;

import mepco.ca.util.Announcer;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.EventListener;

public class ArcadeMonitorController implements ComponentListener {

    public interface Listener extends EventListener {
        void monitorChanged(ArcadeMonitorState monitorState);
    }

    private Announcer<Listener> listeners = Announcer.to(ArcadeMonitorController.Listener.class);
    private ArcadeMonitorState monitorState = new ArcadeMonitorState();
    private Component component;

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        if (this.component != null) {
            this.component.removeComponentListener(this);
        }

        this.component = component;
        this.monitorState = new ArcadeMonitorState();
        this.monitorState.update(this.component);

        if (this.component != null) {
            this.component.addComponentListener(this);
        }

        this.monitorState.update(component);

        listeners.announce().monitorChanged(monitorState);
    }

    public void setMonitorState(ArcadeMonitorState monitorState) {
        if (component != null) {

            // Ensures we don't recieve updated from the component
            component.removeComponentListener(this);

            component.setSize(monitorState.getWidth(), monitorState.getHeight());

            final Graphics2D g = (Graphics2D) component.getGraphics();

            component.setSize(monitorState.getWidth(), monitorState.getHeight());
            component.setVisible(true); // It should always be visible
            component.setBackground(
                monitorState.isPowered()
                ?   monitorState.getBackgroundColor()
                :   Color.BLACK
            );

            g.rotate(monitorState.getOrientationState().radians);
            listeners.announce().monitorChanged(monitorState);

            component.addComponentListener(this);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.monitorState.update(e.getComponent());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        this.monitorState.update(e.getComponent());
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.monitorState.update(e.getComponent());
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        this.monitorState.update(e.getComponent());
    }
}

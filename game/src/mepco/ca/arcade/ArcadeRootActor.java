package mepco.ca.arcade;

import java.awt.*;

import static mepco.ca.util.Util.NANO_PER_SECOND;

public abstract class ArcadeRootActor<World> implements ArcadeActor {
    private long lastTickNanoTime = System.nanoTime();
    private double gameTicksPerSecond = 10.0D;

    private ArcadeControlsState controlsState;
    private ArcadeMonitorState monitorState;
    private ArcadeBindingsState bindingsState;

    private World world;
    private boolean isChanged = true;

    public ArcadeRootActor() {
        setControlsState(new ArcadeControlsState());
        setMonitorState(new ArcadeMonitorState());
        setBindingsState(ArcadeBindingsState.getDefault());
    }

    @Override
    public abstract void tick(double deltaNanoSeconds);

    @Override
    public void render(Graphics2D g) {
        // We will only render if the state has changed
        if (isChanged()) {
            g.setBackground((monitorState.isPowered() ? monitorState.getBackgroundColor() : Color.BLACK));
            g.rotate(monitorState.getOrientationState().radians);
            g.fillRect(0, 0, monitorState.getWidth(), monitorState.getHeight());
            setChanged(false);
        }
    };

    public synchronized ArcadeControlsState getControlsState() {
        return controlsState;
    }

    public synchronized void setControlsState(ArcadeControlsState controlsState) {
        this.controlsState = controlsState;
        setChanged(true);
    }

    public synchronized World getWorld() {
        return world;
    }

    public synchronized void setWorld(World world) {
        this.world = world;
        setChanged(true);
    }

    public abstract void onChange();

    public long getLastTickNanoTime() {
        return lastTickNanoTime;
    }

    public void setLastTickNanoTime(long lastTickNanoTime) {
        this.lastTickNanoTime = lastTickNanoTime;
    }

    public double getGameTicksPerSecond() {
        return gameTicksPerSecond;
    }

    public void setGameTicksPerSecond(double gameTicksPerSecond) {
        this.gameTicksPerSecond = gameTicksPerSecond;
    }

    public final long getNanoPerGameTick() {
        return (long) (NANO_PER_SECOND / getGameTicksPerSecond());
    }

    public synchronized ArcadeMonitorState getMonitorState() {
        return monitorState;
    }

    public synchronized void setMonitorState(ArcadeMonitorState monitorState) {
        this.monitorState = monitorState;
        setChanged(true);
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
        if (changed) {
            onChange();
        }
    }

    public ArcadeBindingsState getBindingsState() {
        return bindingsState;
    }

    public void setBindingsState(ArcadeBindingsState bindingsState) {
        this.bindingsState = bindingsState;
    }
}

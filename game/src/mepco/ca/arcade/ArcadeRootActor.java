package mepco.ca.arcade;

import java.awt.*;

import static mepco.ca.util.Util.NANO_PER_SECOND;

public abstract class ArcadeRootActor<World> implements ArcadeActor {
    private long lastTickNanoTime = System.nanoTime();
    private double gameTicksPerSecond = 10.0D;

    private ArcadeControlsState controlsState;
    private ArcadeMonitorState monitorState;
    private World world;

    public ArcadeRootActor() {
        setControlsState(new ArcadeControlsState());
        setMonitorState(new ArcadeMonitorState());
    }

    @Override
    public abstract void tick(double deltaNanoSeconds);

    @Override
    public void render(Graphics2D g) {
        g.setBackground((monitorState.isPowered() ? monitorState.getBackgroundColor() : Color.BLACK));
        g.rotate(monitorState.getOrientationState().radians);
        g.fillRect(0, 0, monitorState.getWidth(), monitorState.getHeight());
    };

    public synchronized ArcadeControlsState getControlsState() {
        return controlsState;
    }

    public synchronized void setControlsState(ArcadeControlsState controlsState) {
        this.controlsState = controlsState;
        onChange();
    }

    public synchronized World getWorld() {
        return world;
    }

    public synchronized void setWorld(World world) {
        this.world = world;
        onChange();
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
        onChange();
    }
}

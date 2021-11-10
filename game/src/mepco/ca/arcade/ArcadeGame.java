package mepco.ca.arcade;

import mepco.ca.util.Logger;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static mepco.ca.util.Util.MS_PER_SECOND;

public abstract class ArcadeGame extends Canvas implements Runnable, ArcadeInputController.Listener {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final long NANO_PER_SECOND = 1000000000L;

    private Thread thread;
    private boolean running = false;
    protected ArcadeInputController arcadeInputController;
    protected ArcadeMonitorController arcadeMonitorController;
    protected ArcadeView arcadeView;
    protected ArcadeRootActor rootActor;


    public ArcadeGame() {
        this.rootActor = createRootActor();
        this.arcadeView = new ArcadeView(this);
        this.arcadeMonitorController = new ArcadeMonitorController();
        arcadeMonitorController.setComponent(this.arcadeView);

        this.arcadeInputController = new ArcadeInputController();
        this.addKeyListener(this.arcadeInputController);
        this.arcadeInputController.addListener(this);

    }

    public abstract ArcadeRootActor createRootActor();

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            Logger.error(e);
        }
    }


    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = rootActor.getGameTicksPerSecond();
        double ns = NANO_PER_SECOND / amountOfTicks;
        double delta = 0D;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;

            // Inform all the actors that deltaGameTicks have passed.
            tick(delta);

            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > MS_PER_SECOND) {
                timer += MS_PER_SECOND;
                Logger.info("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    protected void tick(double numGameTicks) {
        rootActor.tick(numGameTicks);
    }

    protected void render() {
        final BufferStrategy bs = this.getBufferStrategy();
        if (bs==null) {
            this.createBufferStrategy(2);
            return;
        }

        // I am going to assume we can support the Graphics2D class
        final Graphics2D g = (Graphics2D) bs.getDrawGraphics();


        if (rootActor!=null) {
            rootActor.render(g);
        }

        g.dispose();
        bs.show();

    }


    /**
     * This is baked into all of my arcade games intentionally.
     * Pressing both the player1 start and the player2 start buttons at the same time will kill the application
     * @param arcadeControlsState
     */
    @Override
    public final void onStateChanged(ArcadeControlsState arcadeControlsState) {

        if (isCloseApplicationRequest(arcadeControlsState)) {
            System.exit(0);
        }
        this.rootActor.setControlsState(arcadeControlsState);
    }

    public final boolean isCloseApplicationRequest(ArcadeControlsState state) {
        return (state.p1Start == ButtonState.PRESSED && state.p2Start == ButtonState.PRESSED);
    }
}

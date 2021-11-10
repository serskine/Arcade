package mepco.ca.dodge;

import mepco.ca.arcade.ArcadeActor;
import mepco.ca.arcade.ArcadeRootActor;
import mepco.ca.util.Announcer;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class DodgeRootActor extends ArcadeRootActor {

    private PlayerActor player1;

    private LinkedList<EnemyActor> enemies = new LinkedList<>();
    Announcer<ArcadeActor> stateActors = Announcer.to(ArcadeActor.class);

    public DodgeRootActor() {
        super();

        getMonitorState().setWidth(640);
        getMonitorState().setHeight(640);

        this.setGameTicksPerSecond(60);
        final Point start1 = getRandomPoint(PlayerActor.DEFAULT_RADIUS);

        this.player1 = new PlayerActor(start1.x, start1.y, ActorId.PLAYER_1);

        this.stateActors.addListener(player1);

        int numEnemies = (int) R.nextInt(10) + 1;
        for(int i=0; i<numEnemies; i++) {
            final Point p = getRandomPoint(EnemyActor.DEFAULT_SIZE);
            final EnemyActor enemyActor = new EnemyActor(p.x, p.y);
            this.enemies.add(enemyActor);
            this.stateActors.addListener(enemyActor);
        }
    }

    static final Random R = new Random();


    @Override
    public void tick(double deltaMs) {
        stateActors.announce().tick(deltaMs);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        stateActors.announce().render(g);
    }

    @Override
    public void onChange() {
        if (player1!=null) {
            player1.updatePlayer(getControlsState().joystick1);
        }
    }


    Point getRandomPoint(int size) {
        final Point p = new Point();

        final int w = 640;
        final int h = 480;

        int pX = R.nextInt(w - size*2) + size;
        int pY = R.nextInt(h - size*2) + size;

        p.setLocation(pX, pY);
        return p;
    }


}

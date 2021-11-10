package mepco.ca.dodge;

import java.awt.*;

public class EnemyActor extends DodgeActor<ActorId> {

    public static final int DEFAULT_SIZE = 16;

    protected double size = DEFAULT_SIZE;

    public EnemyActor(double x, double y) {
        super(x, y, ActorId.ENEMY);
        setSize(DEFAULT_SIZE);
        this.setVelocity(50, 50);
    }

    @Override
    public void render(Graphics2D g) {
        final int cX = (int) (x - getSize());
        final int cY = (int) (y - getSize());
        final int diam = (int) getSize() * 2;

        g.setColor(Color.RED);
        g.fillRect(cX, cY, diam, diam);
        g.setColor(Color.YELLOW);
        g.drawRect(cX, cY, diam, diam);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

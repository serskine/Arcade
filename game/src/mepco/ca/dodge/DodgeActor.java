package mepco.ca.dodge;

import mepco.ca.arcade.ArcadeActor;

public abstract class DodgeActor<ActorId> implements ArcadeActor {

    public static final long NANO_PER_MS = 1000000;
    protected double x, y;
    protected ActorId actorId;
    protected double velX, velY;    // Time is in per second

    public DodgeActor(double x, double y, ActorId actorId) {
        this.actorId = actorId;
        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ActorId getActorId() {
        return actorId;
    }

    public void setActorId(ActorId id) {
        this.actorId = id;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void addVelocity(double vX, double vY) {
        this.velX += vX;
        this.velY += vY;
    }

    public void setVelocity(double vX, double vY) {
        this.velX = vX;
        this.velY = vY;
    }

    @Override
    public void tick(double deltaNanoSeconds) {
        this.x += velX * deltaNanoSeconds / NANO_PER_MS;   // Distance is in per seconds
        this.y += velY * deltaNanoSeconds / NANO_PER_MS;   // Distance is in per seconds
    }
}

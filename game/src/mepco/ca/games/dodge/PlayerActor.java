package mepco.ca.games.dodge;

import mepco.ca.arcade.JoystickState;

import java.awt.*;

public class PlayerActor extends DodgeActor<ActorId> {

    public static final int DEFAULT_RADIUS = 16;

    protected double radius;

    private JoystickState joystickState = JoystickState.NEUTRAL;

    public PlayerActor(int x, int y, ActorId actorId) {
        super(x, y, actorId);
        this.radius = DEFAULT_RADIUS;
    }

    @Override
    public void render(Graphics2D g) {
        final int cX = (int) (x - radius);
        final int cY = (int) (y - radius);
        final int diam = (int) radius * 2;

        g.setColor(Color.GREEN);
        g.fillOval(cX, cY, diam, diam);
        g.setColor(Color.RED);
        g.drawOval(cX, cY, diam, diam);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void updatePlayer(JoystickState joystickState) {

        // Unit is the number of pixels we expect to move per game tick
        final double unit = 2*getRadius();
        final double diag = unit * Math.sqrt(2D) / 2D;
        final double none = 0D;

        setJoystickState(joystickState);

        switch (joystickState) {
            case ERROR -> {
                setVelocity(none, none);
            }
            case NEUTRAL -> {
                setVelocity(none, none);
            }
            case N -> {
                setVelocity(none, -unit);
            }
            case NE -> {
                setVelocity(diag, -diag);
            }
            case E -> {
                setVelocity(unit, none);
            }
            case SE -> {
                setVelocity(diag, diag);
            }
            case S -> {
                setVelocity(none, unit);
            }
            case SW -> {
                setVelocity(-diag, diag);
            }
            case W -> {
                setVelocity(-unit, none);
            }
            case NW -> {
                setVelocity(-diag, -diag);
            }
        }

    }

    public JoystickState getJoystickState() {
        return joystickState;
    }

    public void setJoystickState(JoystickState joystickState) {
        this.joystickState = joystickState;
    }


}

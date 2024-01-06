package mepco.ca.util;

import java.io.Serializable;
import java.util.Objects;

public final class Angle implements Comparable<Angle>, Serializable {
    private double valueRadians;

    public static final Angle PI = new Angle(Math.PI, false);
    public static final Angle DEG_360 = new Angle(360D, true);
    public static final Angle DEG_270 = new Angle(270D, true);
    public static final Angle DEG_180 = new Angle(180D, true);
    public static final Angle DEG_90 = new Angle(90D, true);
    public static final Angle DEG_0 = new Angle(0D, true);

    public Angle() {
        this(0D, false);
    }
    public Angle(final double valueRadians) {
        this(valueRadians, false);
    }

    public Angle(final double value, final boolean isDegrees) {
        this.valueRadians = (isDegrees) ? Math.toRadians(value) : value;
    }

    public Angle(final Angle angle) {
        if (angle==null) {
            valueRadians = 0D;
        } else {
            valueRadians = angle.getRadians();
        }
    }

    public double getRadians() {
        return valueRadians;
    }

    public double getDegrees() {
        return Math.toDegrees(valueRadians);
    }

    public void setRadians(final double radians) {
        valueRadians = radians;
    }

    public void setDegrees(final double degrees) {
        setRadians(Math.toRadians(degrees));
    }

    public static double normalizeRadians(double angle) {
        angle = angle % (2D * Math.PI); // Ensure angle is within the range of 0 to 2*Math.PI

        // Adjust angle to be within the range of -Math.PI to +Math.PI
        if (angle > Math.PI) {
            angle -= 2D * Math.PI;
        } else if (angle < -Math.PI) {
            angle += 2D * Math.PI;
        }

        return angle;
    }

    public Angle normalize() {
        return new Angle(normalizeRadians(valueRadians));
    }

    public Angle normalizePositive() {
        double v = normalizeRadians(valueRadians);
        if (v<0D) {
            v += (Math.PI * 2D);
        }
        return new Angle(v);
    }

    public Angle inverse() {
        return new Angle(-valueRadians);
    }

    public Angle add(final Angle a) {
        return new Angle(valueRadians + a.valueRadians);
    }

    public Angle subtract(final Angle a) {
        return new Angle(valueRadians - a.valueRadians);
    }

    @Override
    public int compareTo(Angle o) {
        return Double.compare(valueRadians, o.valueRadians);
    }

    Angle minTurn(final Angle bearing) {
        return bearing.subtract(this).normalize();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof Angle) {
            final double otherValueRadians = ((Angle) other).valueRadians;
            return (valueRadians==otherValueRadians);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%3.1f deg", getDegrees());
    }
}

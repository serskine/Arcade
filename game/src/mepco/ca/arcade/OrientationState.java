package mepco.ca.arcade;


import mepco.ca.util.Util;

public enum OrientationState {
    NORTH(0D),
    EAST(90D),
    SOUTH(180D),
    WEST(270D);

    public final double radians;
    public final double degrees;

    private OrientationState(double degrees) {
        this.degrees = degrees;
        this.radians = degrees * Math.PI / 180D;
    }

    public static OrientationState getClosest(double angleRadians) {
        double p8 = Math.PI/8D;

        double angle = Util.toRangeDouble(angleRadians, -p8, 2D * Math.PI - p8);
        for(OrientationState orientationState : OrientationState.values()) {
            double left = orientationState.radians - p8;
            double right = orientationState.radians + p8;
            if (angle >=left && angle < right) {
                return orientationState;
            }
        }
        throw new RuntimeException("Failed to find the closest orientation state for angle " + angle);
    }
}
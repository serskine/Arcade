package mepco.ca.arcade;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static mepco.ca.arcade.ButtonState.PRESSED;
import static mepco.ca.arcade.ButtonState.RELEASED;

public class ArcadeControlsState {
    public final JoystickState joystick1;
    public final JoystickState joystick2;
    public final ButtonState p1Start;
    public final ButtonState p1Select;
    public final ButtonState p1CoinSlot;
    public final ButtonState p1Top1;
    public final ButtonState p1Top2;
    public final ButtonState p1Top3;
    public final ButtonState p1Bottom1;
    public final ButtonState p1Bottom2;
    public final ButtonState p1Bottom3;
    public final ButtonState p2Start;
    public final ButtonState p2Select;
    public final ButtonState p2CoinSlot;
    public final ButtonState p2Top1;
    public final ButtonState p2Top2;
    public final ButtonState p2Top3;
    public final ButtonState p2Bottom1;
    public final ButtonState p2Bottom2;
    public final ButtonState p2Bottom3;
    public final ButtonState flipperLeft;
    public final ButtonState flipperRight;

    public final Set<ArcadeButton> pressedButtons;

    public ArcadeControlsState() {
        this(new HashSet<>());
    }
    public ArcadeControlsState(final Set<ArcadeButton> thePressedArcadeButtons) {

        this.pressedButtons = Collections.unmodifiableSet(thePressedArcadeButtons);

        this.flipperLeft = (pressedButtons.contains(ArcadeButton.LEFT_FLIPPER) ? PRESSED : RELEASED);
        this.flipperRight = (pressedButtons.contains(ArcadeButton.RIGHT_FLIPPER) ? PRESSED : RELEASED);

        this.p1CoinSlot = (pressedButtons.contains(ArcadeButton.P1_COIN_SLOT) ? PRESSED : RELEASED);
        this.p1Start = (pressedButtons.contains(ArcadeButton.P1_START) ? PRESSED : RELEASED);
        this.p1Select = (pressedButtons.contains(ArcadeButton.P1_SELECT) ? PRESSED : RELEASED);
        this.p1Top1 = (pressedButtons.contains(ArcadeButton.P1_TOP_1) ? PRESSED : RELEASED);
        this.p1Top2 = (pressedButtons.contains(ArcadeButton.P1_TOP_2) ? PRESSED : RELEASED);
        this.p1Top3 = (pressedButtons.contains(ArcadeButton.P1_TOP_3) ? PRESSED : RELEASED);
        this.p1Bottom1 = (pressedButtons.contains(ArcadeButton.P1_BOTTOM_1) ? PRESSED : RELEASED);
        this.p1Bottom2 = (pressedButtons.contains(ArcadeButton.P1_BOTTOM_2) ? PRESSED : RELEASED);
        this.p1Bottom3 = (pressedButtons.contains(ArcadeButton.P1_BOTTOM_3) ? PRESSED : RELEASED);

        this.p2CoinSlot = (pressedButtons.contains(ArcadeButton.P2_COIN_SLOT) ? PRESSED : RELEASED);
        this.p2Start = (pressedButtons.contains(ArcadeButton.P2_START) ? PRESSED : RELEASED);
        this.p2Select = (pressedButtons.contains(ArcadeButton.P2_SELECT) ? PRESSED : RELEASED);
        this.p2Top1 = (pressedButtons.contains(ArcadeButton.P2_TOP_1) ? PRESSED : RELEASED);
        this.p2Top2 = (pressedButtons.contains(ArcadeButton.P2_TOP_2) ? PRESSED : RELEASED);
        this.p2Top3 = (pressedButtons.contains(ArcadeButton.P2_TOP_3) ? PRESSED : RELEASED);
        this.p2Bottom1 = (pressedButtons.contains(ArcadeButton.P2_BOTTOM_1) ? PRESSED : RELEASED);
        this.p2Bottom2 = (pressedButtons.contains(ArcadeButton.P2_BOTTOM_2) ? PRESSED : RELEASED);
        this.p2Bottom3 = (pressedButtons.contains(ArcadeButton.P2_BOTTOM_3) ? PRESSED : RELEASED);

        this.joystick1 = toJoystickState(
                pressedButtons.contains(ArcadeButton.P1_UP),
                pressedButtons.contains(ArcadeButton.P1_RIGHT),
                pressedButtons.contains(ArcadeButton.P1_DOWN),
                pressedButtons.contains(ArcadeButton.P1_LEFT)
        );

        this.joystick2 = toJoystickState(
                pressedButtons.contains(ArcadeButton.P2_UP),
                pressedButtons.contains(ArcadeButton.P2_RIGHT),
                pressedButtons.contains(ArcadeButton.P2_DOWN),
                pressedButtons.contains(ArcadeButton.P2_LEFT)
        );
    }

    public static final JoystickState toJoystickState(boolean up, boolean right, boolean down, boolean left) {
        if (!up && !down && !left && !right) {
            return JoystickState.NEUTRAL;
        } else if (up && !left && !right && !down) {
            return JoystickState.N;
        } else if (up && left && !right && !down) {
            return JoystickState.NW;
        } else if (up && !left && right && !down) {
            return JoystickState.NE;
        } else if (!up && left && !right && !down) {
            return JoystickState.W;
        } else if (!up && !left && right && !down) {
            return JoystickState.E;
        } else if (!up && left && !right && down) {
            return JoystickState.SW;
        } else if (!up && !left && !right && down) {
            return JoystickState.S;
        } else if (!up && !left && right && down) {
            return JoystickState.SE;
        } else {
            return JoystickState.ERROR;
        }
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder();
        final String tab = "   ";
        final String newLine = "\n";
        sb.append("{").append(newLine)
            .append(tab).append("joystick1:").append(joystick1).append(newLine)
            .append(tab).append("p1Start:").append(p1Start).append(newLine)
            .append(tab).append("p1Select:").append(p1Select).append(newLine)
            .append(tab).append("p1CoinSlot:").append(p1CoinSlot).append(newLine)
            .append(tab).append("p1Top1:").append(p1Top1).append(newLine)
            .append(tab).append("p1Top2:").append(p1Top2).append(newLine)
            .append(tab).append("p1Top3:").append(p1Top3).append(newLine)
            .append(tab).append("p1Bottom1:").append(p1Bottom1).append(newLine)
            .append(tab).append("p1Bottom2:").append(p1Bottom2).append(newLine)
            .append(tab).append("p1Bottom3:").append(p1Bottom3).append(newLine)
            .append(tab).append("p2Start:").append(p2Start).append(newLine)
            .append(tab).append("p2Select:").append(p2Select).append(newLine)
            .append(tab).append("p2CoinSlot:").append(p2CoinSlot).append(newLine)
            .append(tab).append("p2Top1:").append(p2Top1).append(newLine)
            .append(tab).append("p2Top2:").append(p2Top2).append(newLine)
            .append(tab).append("p2Top3:").append(p2Top3).append(newLine)
            .append(tab).append("p2Bottom1:").append(p2Bottom1).append(newLine)
            .append(tab).append("p2Bottom2:").append(p2Bottom2).append(newLine)
            .append(tab).append("p2Bottom3:").append(p2Bottom3).append(newLine)
            .append(tab).append("flipperLeft:").append(flipperLeft).append(newLine)
            .append(tab).append("flipperRight:").append(flipperRight).append(newLine)
            .append("}").append(newLine);

        return sb.toString();
    }
}

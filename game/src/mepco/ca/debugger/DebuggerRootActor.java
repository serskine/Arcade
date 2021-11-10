package mepco.ca.debugger;

import mepco.ca.arcade.*;
import mepco.ca.util.Text;
import mepco.ca.util.draw.Draw;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class DebuggerRootActor extends ArcadeRootActor {

    public static final Color COLOR_ERROR = Color.RED;
    public static final Color COLOR_ACTIVE = Color.GREEN;
    public static final Color COLOR_FOREGROUND = Color.YELLOW;
    public static final Color COLOR_BACKGROUND = Color.BLACK;
    public static final Color COLOR_ACCENT = Color.WHITE;


    public static final int UNIT = 18;

    public static final int FLIPPER_WIDTH = UNIT * 12;
    public static final int FLIPPER_HEIGHT = UNIT;
    public static final int JOYSTICK_RADIUS = UNIT*3;
    public static final int JOYSTICK_DIAM = JOYSTICK_RADIUS * 2;
    public static final int BUTTON_RADIUS = UNIT;
    public static final int BUTTON_DIAM = BUTTON_RADIUS * 2;
    public static final int COIN_SLOT_WIDTH = BUTTON_DIAM;
    public static final int COIN_SLOT_HEIGHT = COIN_SLOT_WIDTH * 3 / 2;
    public static final int CONTROLLER_HEIGHT = (2 * UNIT) + Math.max(
            JOYSTICK_DIAM,
            (2 * BUTTON_DIAM) + BUTTON_RADIUS + UNIT + FLIPPER_HEIGHT
    );
    public static final int CONTROLLER_WIDTH = UNIT + JOYSTICK_DIAM + (UNIT + BUTTON_DIAM) * 6 + UNIT + COIN_SLOT_WIDTH;

    public static final int CONTROLS_AREA_WIDTH = CONTROLLER_WIDTH;
    public static final int CONTROLS_AREA_HEIGHT = CONTROLLER_HEIGHT*2 + UNIT + FLIPPER_HEIGHT + UNIT;

    public static final float JOYSTICK_THICKNESS = 10f;
    public static final int MONITOR_AREA_HEIGHT = CONTROLS_AREA_HEIGHT;
    public static final int MONITOR_AREA_WIDTH = 3 * MONITOR_AREA_HEIGHT / 4;
    public static final int BINDINGS_AREA_WIDTH = MONITOR_AREA_WIDTH + UNIT + CONTROLS_AREA_WIDTH;

    public static final int BINDING_WIDTH = BINDINGS_AREA_WIDTH /2;
    public static final int BINDING_HEIGHT = UNIT*2;

    public static final int TEXT_HEIGHT_TITLE = UNIT*2;
    public static final int TEXT_HEIGHT_MONITOR = TEXT_HEIGHT_TITLE;
    public static final int TEXT_HEIGHT_BINDING = UNIT;

    public DebuggerRootActor() {
        super();
        getMonitorState().setBackgroundColor(Color.BLACK);
    }
    
    @Override
    public void onChange() {

    }

    @Override
    public void tick(double deltaNanoSeconds) {

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);


        final int width = getMonitorState().getWidth();
        final int height = getMonitorState().getHeight();

        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, width, height);

        final String title = "Arcade Debugger";
        final Dimension textSize = Draw.getTextSize(g, TEXT_HEIGHT_TITLE, title);
        final int tX = (width-textSize.width)/2;
        final int tY = TEXT_HEIGHT_TITLE;

        final Point pTitle = new Point(tX, tY);
        Draw.renderRetroText(g, pTitle, TEXT_HEIGHT_TITLE, COLOR_ACCENT, COLOR_FOREGROUND, title);

        final int controlsWidth = MONITOR_AREA_WIDTH + UNIT + CONTROLS_AREA_WIDTH;
        final int hpad = (width - controlsWidth) / 2;
        final int vpad = pTitle.y + textSize.height + UNIT;

        final Point pMonitor = new Point(hpad, vpad);
        final Point pControls = new Point(pMonitor.x + MONITOR_AREA_WIDTH + UNIT, pMonitor.y);
        final Point pBindings = new Point(pMonitor.x, pControls.y + UNIT + CONTROLS_AREA_HEIGHT);

        renderMonitorState(g, pMonitor, getMonitorState());
        renderControlsState(g, pControls, getControlsState());
        renderBindingsState(g, pBindings, getBindingsState());

    }

    private void renderBindingsState(Graphics2D g, Point p, ArcadeBindingsState bindingsState) {

        final int div = ArcadeButton.values().length / 2;
        final int colLeftX = p.x;
        final int colRightX = p.x + BINDINGS_AREA_WIDTH/2;
        final int numButtons = ArcadeButton.values().length;
        for(int i=0; i<numButtons; i++) {
            final ArcadeButton arcadeButton = ArcadeButton.values()[i];
            final int bindingY = p.y +  (    (i > div)
                                        ?   (BINDING_HEIGHT * (i - div - 1))
                                        :   BINDING_HEIGHT * i
                                        );
            Point pBinding;
            pBinding = (i<=div)
                    ?   new Point(colLeftX, bindingY)
                    :   new Point(colRightX, bindingY);

            final Optional<BindingState> bindingOpt = bindingsState.getBindingFromButton(arcadeButton);
            renderBinding(g, pBinding, arcadeButton, bindingOpt);

        }
    }

    private void renderBinding(Graphics2D g, Point p, ArcadeButton button, Optional<BindingState> bindingStateOptional) {
        final Color color = (bindingStateOptional.isPresent()) ? COLOR_ACTIVE : COLOR_ERROR;
        final int bTextWidth = Arrays.stream(ArcadeButton.values()).sequential().map(b -> b.toString().length()).reduce(0, (l,r) -> Math.max(l,r));

        g.setColor(color);

        g.drawRect(p.x, p.y, BINDING_WIDTH, BINDING_HEIGHT);

        final String buttonText = button.toString();
        final String codeText = (bindingStateOptional.isEmpty())
                                ?   ""
                                :   "" + Integer.valueOf(bindingStateOptional.get().code).toString()
        ;

        final int pad = (BINDING_HEIGHT - TEXT_HEIGHT_BINDING) / 2;
        final Point pButton = new Point(p.x + pad, p.y + pad);

        final String outputText = Text.fString(buttonText, bTextWidth) + " : " + codeText;
        Draw.renderRetroText(g, pButton, TEXT_HEIGHT_BINDING, null, color, outputText);
    }

    private void renderMonitorState(Graphics2D g, Point p, ArcadeMonitorState monitorState) {
        int x = p.x;
        int y = p.y;
        int nW = MONITOR_AREA_WIDTH;
        int nH = MONITOR_AREA_HEIGHT;

        if (monitorState.isPowered()) {
            g.setColor(COLOR_ACTIVE);
        } else {
            g.setColor(COLOR_ERROR);
        }

        g.fillRect(x, y, nW, nH);

        g.setColor(COLOR_FOREGROUND);
        g.drawRect(x, y, nW, nH);

        final String wText = "" + getMonitorState().getWidth();
        final String hText = "" + getMonitorState().getHeight();
        final String opText = "x";


        Dimension wSize = Draw.getTextSize(g, TEXT_HEIGHT_MONITOR, wText);
        Dimension hSize = Draw.getTextSize(g, TEXT_HEIGHT_MONITOR, hText);
        Dimension opSize = Draw.getTextSize(g, TEXT_HEIGHT_MONITOR, opText);

        final int pWidthX = p.x + (MONITOR_AREA_WIDTH-wSize.width)/2;
        final int pOpX = p.x + (MONITOR_AREA_WIDTH-opSize.width)/2;
        final int pHeightX = p.x + (MONITOR_AREA_WIDTH-hSize.width)/2;

        final int innerHeight = MONITOR_AREA_HEIGHT - wSize.height - opSize.height - hSize.height - TEXT_HEIGHT_MONITOR - TEXT_HEIGHT_MONITOR;
        final int pWidthY = p.y + (MONITOR_AREA_HEIGHT - innerHeight) / 2;
        final int pOpY = pWidthY + wSize.height + TEXT_HEIGHT_MONITOR;
        final int pHeightY = pOpY + opSize.height + TEXT_HEIGHT_MONITOR;

        final Point pWidth = new Point(pWidthX, pWidthY);
        final Point pOp = new Point(pOpX, pOpY);
        final Point pHeight = new Point(pHeightX, pHeightY);

        Draw.renderRetroText(g, pWidth, TEXT_HEIGHT_MONITOR, COLOR_ACCENT, COLOR_FOREGROUND, wText);
        Draw.renderRetroText(g, pOp, TEXT_HEIGHT_MONITOR, COLOR_ACCENT, COLOR_FOREGROUND, opText);
        Draw.renderRetroText(g, pHeight, TEXT_HEIGHT_MONITOR, COLOR_ACCENT, COLOR_FOREGROUND, hText);

    }

    private void renderFlipper(Graphics2D g, Point p, ButtonState leftFlipper) {

        switch(leftFlipper) {
            case ERROR -> {
                g.setColor(COLOR_ERROR);
            }
            case RELEASED -> {
                g.setColor(COLOR_FOREGROUND);
            }
            case PRESSED -> {
                g.setColor(COLOR_ACTIVE);
            }
        }
        g.fillRect(p.x, p.y, FLIPPER_WIDTH, FLIPPER_HEIGHT);
    }

    private void renderControlsState(Graphics2D g, Point p, ArcadeControlsState controlsState) {
        final Point p1 = new Point(p.x, p.y);
        final Point p2 = new Point(p.x, p.y + UNIT + CONTROLLER_HEIGHT);

        renderPlayerControls(g,
                p1,
                controlsState.joystick1,
                controlsState.p1Top1,
                controlsState.p1Top2,
                controlsState.p1Top3,
                controlsState.p1Bottom1,
                controlsState.p1Bottom2,
                controlsState.p1Bottom3,
                controlsState.p1Start,
                controlsState.p1Select,
                controlsState.p1CoinSlot
        );
        renderPlayerControls(g,
                p2,
                controlsState.joystick2,
                controlsState.p2Top1,
                controlsState.p2Top2,
                controlsState.p2Top3,
                controlsState.p2Bottom1,
                controlsState.p2Bottom2,
                controlsState.p2Bottom3,
                controlsState.p2Start,
                controlsState.p2Select,
                controlsState.p2CoinSlot
        );

        final int padFlipper = UNIT;

        int flipperY = p2.y + UNIT + CONTROLLER_HEIGHT;

        final Point pLeftFlipper = new Point(p.x + padFlipper, flipperY);
        final Point pRightFlipper = new Point(p.x + CONTROLLER_WIDTH - padFlipper - FLIPPER_WIDTH, flipperY);

        renderFlipper(g, pLeftFlipper, controlsState.flipperLeft);
        renderFlipper(g, pRightFlipper, controlsState.flipperRight);
    }

    public void renderPlayerControls(
        Graphics2D g,
        Point p,
        JoystickState joystickState,
        ButtonState topLeft,
        ButtonState topMiddle,
        ButtonState topRight,
        ButtonState bottomLeft,
        ButtonState bottomMiddle,
        ButtonState bottomRight,
        ButtonState startButton,
        ButtonState selectButton,
        ButtonState coinSlot
    ) {
        final int innerWidth = CONTROLLER_WIDTH - UNIT - UNIT;
        final int innerHeight = CONTROLLER_HEIGHT - UNIT - UNIT;
        final Point pInnerOrigin = new Point(p.x + UNIT, p.y + UNIT);
        final int joyPad = Math.min(innerHeight/2 - JOYSTICK_RADIUS, innerWidth/2 - JOYSTICK_RADIUS);
        final Point pJoy = new Point(pInnerOrigin.x + joyPad, pInnerOrigin.y + joyPad);

        g.setColor(COLOR_BACKGROUND);
        g.fillRect(p.x, p.y, CONTROLLER_WIDTH, CONTROLLER_HEIGHT);

        g.setColor(COLOR_FOREGROUND);
        g.drawRect(p.x, p.y, CONTROLLER_WIDTH, CONTROLLER_HEIGHT);

        final Point pCoinSlot = new Point(pInnerOrigin.x + JOYSTICK_DIAM + UNIT, pInnerOrigin.y + JOYSTICK_RADIUS - BUTTON_RADIUS);
        final Point pStartButton = new Point(pCoinSlot.x + COIN_SLOT_WIDTH + UNIT, pCoinSlot.y);
        final Point pSelectButton = new Point(pStartButton.x + BUTTON_DIAM + UNIT, pStartButton.y);

        final Point pTopLeftButton = new Point(pSelectButton.x + BUTTON_DIAM + UNIT, pInnerOrigin.y);
        final Point pTopMiddleButton = new Point(pTopLeftButton.x + BUTTON_DIAM + UNIT, pTopLeftButton.y);
        final Point pTopRightButton = new Point(pTopMiddleButton.x + BUTTON_DIAM + UNIT, pTopMiddleButton.y);

        final Point pBottomLeftButton = new Point(pTopLeftButton.x + UNIT, pTopLeftButton.y + BUTTON_DIAM + UNIT);
        final Point pBottomMiddleButton = new Point(pBottomLeftButton.x + BUTTON_DIAM + UNIT, pBottomLeftButton.y);
        final Point pBottomRightButton = new Point(pBottomMiddleButton.x + BUTTON_DIAM + UNIT, pBottomMiddleButton.y);

        renderJoystick(g, pJoy, joystickState);

        renderButton(g, pStartButton, startButton);
        renderButton(g, pSelectButton, selectButton);

        renderButton(g, pTopLeftButton, topLeft);
        renderButton(g, pTopMiddleButton, topMiddle);
        renderButton(g, pTopRightButton, topRight);
        renderButton(g, pBottomLeftButton, bottomLeft);
        renderButton(g, pBottomMiddleButton, bottomMiddle);
        renderButton(g, pBottomRightButton, bottomRight);

        renderCoinSlot(g, pCoinSlot, coinSlot);
    }

    private Color buttonColor(ButtonState buttonState) {
        switch (buttonState) {

            case ERROR -> {
                return COLOR_ERROR;
            }
            case RELEASED -> {
                return COLOR_FOREGROUND;
            }
            case PRESSED -> {
                return COLOR_ACTIVE;
            }
            default -> throw new IllegalStateException("Unexpected value: " + buttonState);
        }
    }

    private void renderCoinSlot(Graphics2D g, Point p, ButtonState buttonState) {

        g.setColor(buttonColor(buttonState));
        g.fillRect(p.x, p.y, COIN_SLOT_WIDTH, COIN_SLOT_HEIGHT);

        final int innerPadding = COIN_SLOT_WIDTH/4;
        final int innerWidth = COIN_SLOT_WIDTH/6;
        final int innerHeight = COIN_SLOT_HEIGHT - (innerPadding * 2);
        final int innerX = p.x + COIN_SLOT_WIDTH - innerPadding - innerWidth;
        final int innerY = p.y + innerPadding;

        g.setColor(COLOR_BACKGROUND);
        g.fillRect(innerX, innerY, innerWidth, innerHeight);
    }

    private void renderButton(Graphics2D g, Point p, ButtonState buttonState) {
        g.setColor(buttonColor(buttonState));
        g.fillOval(p.x, p.y, BUTTON_DIAM, BUTTON_DIAM);

        g.setColor(COLOR_BACKGROUND);
        g.drawOval(p.x, p.y, BUTTON_DIAM, BUTTON_DIAM);
    }

    private void renderJoystick(Graphics2D g, Point p, JoystickState joystickState) {

        final int innerRadius = JOYSTICK_RADIUS/4;
        final int innerDiam = innerRadius * 2;
        final int innerX = p.x + JOYSTICK_RADIUS - innerRadius;
        final int innerY = p.y + JOYSTICK_RADIUS - innerRadius;


        switch(joystickState) {
            case ERROR      -> {    g.setColor(Color.RED);      }
            case NEUTRAL    -> {    g.setColor(Color.YELLOW);   }
            default         -> {    g.setColor(Color.GREEN);    }
        }


        g.fillOval(p.x, p.y, JOYSTICK_DIAM, JOYSTICK_DIAM);

        g.setColor(COLOR_BACKGROUND);
        g.drawOval(p.x, p.y, JOYSTICK_DIAM, JOYSTICK_DIAM);


        final int unit = JOYSTICK_RADIUS;
        final int diag = (int) (unit * Math.sqrt(2D) / 2D);
        final int none = 0;

        final int N_START = 12;

        final Point p1 = new Point(p.x + JOYSTICK_RADIUS, p.y + JOYSTICK_RADIUS);

        g.setColor(COLOR_BACKGROUND);
        g.fillOval(innerX, innerY, innerDiam, innerDiam);

        final Point p2 = getJoystickEnd(p1, joystickState);
        final Stroke prevStroke = g.getStroke();
        final Stroke thickStroke = new BasicStroke(JOYSTICK_THICKNESS);
        g.setStroke(thickStroke);

        g.drawLine(p1.x, p1.y, p2.x, p2.y);

        g.setStroke(prevStroke);
    }


    Point getJoystickEnd(Point p1, JoystickState joystickState) {
        final int unit = JOYSTICK_RADIUS;
        final int diag = (int) (unit * Math.sqrt(2D) / 2D);
        final int none = 0;
        
        switch (joystickState) {
            case ERROR      -> {    return p1;                                            }
            case NEUTRAL    -> {    return p1;                                            }
            case N          -> {    return new Point(p1.x + none, p1.y - unit);     }
            case NE         -> {    return new Point(p1.x + diag, p1.y - diag);     }
            case E          -> {    return new Point(p1.x + unit, p1.y - none);     }
            case SE         -> {    return new Point(p1.x + diag, p1.y + diag);     }
            case S          -> {    return new Point(p1.x + none, p1.y + unit);     }
            case SW         -> {    return new Point(p1.x - diag, p1.y + diag);     }
            case W          -> {    return new Point(p1.x - unit, p1.y + none);     }
            case NW         -> {    return new Point(p1.x - diag, p1.y - diag);     }
            default         -> {    return p1;                                            }
        }
    }


}

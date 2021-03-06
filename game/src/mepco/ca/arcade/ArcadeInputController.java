package mepco.ca.arcade;

import mepco.ca.util.Announcer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ArcadeInputController implements KeyListener {

    private final Announcer<Listener> listeners = Announcer.to(Listener.class);
    private final Set<Integer> pressedCodes = new HashSet<>();

    private ArcadeBindingsState bindingsState = ArcadeBindingsState.getEmpty();

    public ArcadeInputController() {
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing. Ignore. Only listen to the pressed and released buttons
    }

    @Override
    public void keyPressed(KeyEvent e) {
        synchronized (pressedCodes) {
            pressedCodes.add(e.getKeyCode());
            announceState();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        synchronized (pressedCodes) {
            pressedCodes.remove(e.getKeyCode());
            announceState();
        }
    }

    public ArcadeControlsState getState() {
        final Set<ArcadeButton> pressedArcadeButtons;
        synchronized (pressedCodes) {
            pressedArcadeButtons = getPressedButtons();
        }
        final ArcadeControlsState state = new ArcadeControlsState(pressedArcadeButtons);
        return state;
    }



    public void addListener(Listener listener) {
        this.listeners.addListener(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.removeListener(listener);
    }

    public void announceState() {
        final ArcadeControlsState arcadeState = getState();

        listeners.announce().onStateChanged(arcadeState);
    }

    public final Set<ArcadeButton> getPressedButtons() {

        synchronized (pressedCodes) {
            return pressedCodes.stream()
                    .map(code -> this.findButton(code))
                    .filter(buttonOpt -> buttonOpt.isPresent())
                    .map(buttonOpt -> buttonOpt.get())
                    .collect(Collectors.toSet());
        }
    }

    /**
     * We did not find a button for the binding Therefore it can't be pressed.564545454554
     * @param code
     * @return
     */
    public final Optional<ArcadeButton> findButton(int code) {
        if (bindingsState == null) {
            return Optional.empty(); // This is to slow. Speed up.
        } else {
            final Optional<BindingState> bindingOpt = bindingsState.getBindingFromCode(code);
            return bindingOpt.isEmpty() ? Optional.empty() : Optional.of(bindingOpt.get().button);
        }
    }

    public void setBindingsState(ArcadeBindingsState bindingsState) {
        this.bindingsState = bindingsState;
    }


    public interface Listener extends EventListener {
        void onStateChanged(ArcadeControlsState state);
    }

}

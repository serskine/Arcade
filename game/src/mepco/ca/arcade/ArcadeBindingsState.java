package mepco.ca.arcade;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class ArcadeBindingsState {
    private final Map<ArcadeButton, Integer> buttonToCodeMap;
    private final Map<Integer, ArcadeButton> codeToButtonMap;

    public static final ArcadeBindingsState getDefault() {
        final ArcadeBindingsState arcadeBindingsState = new ArcadeBindingsState();
        arcadeBindingsState.initDefaults();
        return arcadeBindingsState;
    }

    public static final ArcadeBindingsState getEmpty() {
        final ArcadeBindingsState arcadeBindingsState = new ArcadeBindingsState();
        return arcadeBindingsState;
    }

    private ArcadeBindingsState() {
        buttonToCodeMap = new HashMap<>();
        codeToButtonMap = new HashMap<>();
    }

    /**
     * This will initialize the button binding to the default.
     */
    public synchronized final void initDefaults() {
        buttonToCodeMap.clear();
        codeToButtonMap.clear();
        for(ArcadeButton button : ArcadeButton.values()) {
            buttonToCodeMap.put(button, button.code);
            codeToButtonMap.put(button.code, button);
        }
    }

    public synchronized final Optional<BindingState> getBindingFromCode(int keyCode) {
        final ArcadeButton value = codeToButtonMap.get(keyCode);
        if (value==null) {
            return Optional.empty();
        } else {
            return Optional.of(new BindingState(keyCode, value));
        }
    }

    public synchronized final Optional<BindingState> getBindingFromButton(ArcadeButton button) {
        final Integer value = buttonToCodeMap.get(button);
        if (value==null) {
            return Optional.empty();
        } else {
            return Optional.of(new BindingState(value, button));
        }
    }

    public synchronized Optional<BindingState> removeBindingFromCode(int keyCode) {
        final Optional<BindingState> prevBindingOpt = getBindingFromCode(keyCode);
        if (prevBindingOpt.isPresent()) {
            final BindingState prevBinding = prevBindingOpt.get();
            buttonToCodeMap.remove(prevBinding.button);
            codeToButtonMap.remove(prevBinding.code);
        }
        return prevBindingOpt;
    }


    public synchronized Optional<BindingState> removeBindingFromButton(ArcadeButton button) {
        final Optional<BindingState> prevBindingOpt = getBindingFromButton(button);
        if (prevBindingOpt.isPresent()) {
            final BindingState prevBinding = prevBindingOpt.get();
            buttonToCodeMap.remove(prevBinding.button);
            codeToButtonMap.remove(prevBinding.code);
        }
        return prevBindingOpt;
    }


    /**
     * This will remove the previous bindings to both the code and the button
     * @param binding
     */
    public synchronized void setBinding(BindingState binding) {

        removeBindingFromButton(binding.button);
        removeBindingFromCode(binding.code);

        codeToButtonMap.put(binding.code, binding.button);
        buttonToCodeMap.put(binding.button, binding.code);
    }

    /**
     * This will return a list of all bindings present.
     * @return
     */
    public synchronized List<BindingState> getBindings() {
        return codeToButtonMap.entrySet().stream().map(e -> new BindingState(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    /**
     * We only care about what buttons are not bound.
     * @return
     */
    public synchronized Set<ArcadeButton> getUnboundButtons() {
        final List<ArcadeButton> allButtons = Arrays.asList(ArcadeButton.values());
        return allButtons.stream().filter(b -> getBindingFromButton(b).isPresent()).collect(Collectors.toSet());
    }
}

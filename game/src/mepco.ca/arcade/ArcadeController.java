package mepco.ca.arcade;

import mepco.ca.util.Announcer;
import mepco.ca.util.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class ArcadeController implements KeyListener {


    private Map<Character, Binding> bindingMap;
    private Set<Character> pressedSet;
    private Announcer<Listener> listeners;

    public interface Listener extends EventListener {
        void onBindingPressed(Binding binding);
        void onBindingReleased(Binding binding);
    }

    /**
     * properties is the path to a properties file on the system that will specify all the key bindings for the buttons.
     * @param properties
     */
    public ArcadeController(final String properties) {
        this.bindingMap = loadBindings(properties);
        this.pressedSet = new HashSet<>();
        this.listeners = new Announcer<>(Listener.class);
    }

    public void setBindings(final Map<Character, Binding> bindingsMap) {
        this.bindingMap = bindingsMap;
        this.pressedSet = new HashSet<>();
    }



    public Map<Character, Binding> loadBindings(final String propertiesFile) {
        Logger.info("TODO: Implement loading the bindings from properties file \"" + propertiesFile + "\"");
        final Map<Character, Binding> bindingsMap = new HashMap<>();
        for(Button button : Button.values()) {
            bindingsMap.put(button.defaultKey, new Binding(1, button.defaultKey, button));
        }
        return bindingsMap;
    }

    @Override
    public final void keyTyped(KeyEvent e) {
        // Do nothing. We will only do things on press or release
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final Binding binding = bindingMap.getOrDefault(e.getKeyChar(), null);
        if (binding != null) {
            setPressed(binding.character, true);
            listeners.announce().onBindingPressed(binding);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        final Binding binding = bindingMap.getOrDefault(e.getKeyChar(), null);
        if (binding != null) {
            setPressed(binding.character, false);
            listeners.announce().onBindingReleased(binding);
        }
    }



    public boolean isPressed(Character c) {
        return pressedSet.contains(c);
    }

    public void setPressed(Character c, boolean isPressed) {
        if (isPressed) {
            pressedSet.add(c);
        } else {
            pressedSet.remove(c);
        }
    }
}

package mepco.ca.arcade;

import mepco.ca.util.Util;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyCodeTest {

    private final Set<Character> pressedCharacters = new HashSet<>();
    private final Set<Integer> pressedCodes = new HashSet<>();

    private final KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            pressedCharacters.add(e.getKeyChar());
            pressedCodes.add(e.getKeyCode());

            String message = "Key pressed:\n char(" + e.getKeyChar() + "), code(" + e.getKeyCode() + ")\n"
                    + " codes: " + Util.describeSet(pressedCodes) + "\n";
            System.out.println(message);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pressedCharacters.remove(e.getKeyChar());
            pressedCodes.remove(e.getKeyCode());

            String message = "Key released:\n char(" + e.getKeyChar() + "), code(" + e.getKeyCode() + ")\n"
                    + " codes: " + Util.describeSet(pressedCodes) + "\n";

            System.out.println();
            System.out.println(message);
        }
    };

    @Disabled
    @Test
    public void smokeTest() {
        final JFrame frame = new JFrame("Key Listener");
        final Container contentPane = frame.getContentPane();
        JTextField textField = new JTextField();
        textField.addKeyListener(keyListener);
        contentPane.add(textField, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

        final int escapeCode = 27;
        while(!pressedCodes.contains(escapeCode)) {

        }
    }

}

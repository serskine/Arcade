package mepco.ca.arcade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class ArcadeControllerTest {

    private JFrame frame;
    private Container contentPane;

    JTextField textField = new JTextField();
    private ArcadeInputController arcadeController;

    final ArcadeInputController.Listener listener = new ArcadeInputController.Listener() {
        @Override
        public void onStateChanged(ArcadeControlsState state) {
            final String line = "============================================================\n";
            System.out.println(line + state.toPrettyString() + line);
        }
    };

    @BeforeEach
    public void onSetup() {
        frame = new JFrame(getClass().getSimpleName());
        contentPane = frame.getContentPane();
        arcadeController = new ArcadeInputController();

        textField.addKeyListener(arcadeController);
        contentPane.add(textField, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

    }

    @Disabled
    @Test
    public void smokeTest() {
        arcadeController.addListener(listener);

    }
}

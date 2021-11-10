package mepco.ca.arcade;

import mepco.ca.util.Logger;

import javax.swing.*;
import java.awt.*;

public class ArcadeView extends Canvas {

    final JFrame frame;
    final ArcadeGame game;

    public ArcadeView(ArcadeGame game) {
        this.frame = new JFrame();
        this.game = game;

        final ArcadeMonitorState monitorState = game.rootActor.getMonitorState();

        if (monitorState==null) {
            throw new RuntimeException("Game monitor state is not initialized on the root actor");
        }
        final Dimension implementedDimension = new Dimension(monitorState.getWidth(), monitorState.getHeight());
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Logger.info("Implemented View Size = " + implementedDimension.toString());
        Logger.info("Actual Screen Size = " + screenSize.toString());

        frame.setPreferredSize(implementedDimension);
        frame.setMaximumSize(implementedDimension);
        frame.setMinimumSize(implementedDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setUndecorated(true);
        frame.setVisible(true);

        this.addKeyListener(game.arcadeInputController);
        this.addComponentListener(game.arcadeMonitorController);

        monitorState.setPowered(true);

        game.start();

    }
}

package mepco.ca.arcade;

import java.awt.*;
import java.util.EventListener;

public interface ArcadeActor extends EventListener {
    void tick(double deltaMs);
    void render(Graphics2D g);
}

package mepco.ca.arcade.engine3d;

import mepco.ca.arcade.ArcadeActor;
import mepco.ca.util.draw.Pipeline3D;
import mepco.ca.util.matrix.Matrix4D;
import mepco.ca.util.matrix.Mesh3D;

import java.awt.*;

public interface Actor3D {
    void render(Graphics2D g, Pipeline3D p);
}

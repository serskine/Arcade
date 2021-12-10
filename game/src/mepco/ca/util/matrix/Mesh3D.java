package mepco.ca.util.matrix;

import mepco.ca.arcade.engine3d.Actor3D;
import mepco.ca.util.draw.Pipeline3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mesh3D implements Actor3D {
    public final List<Triangle3D> triangles = new ArrayList<>();

    public Mesh3D(Triangle3D... triangles) {
        assert(triangles != null);
        assert(triangles.length > 0);

        for(Triangle3D t : triangles) {
            this.triangles.add(t);
        }
    }

    @Override
    public void render(Graphics2D g, Pipeline3D pipeline) {
        for(Triangle3D t : triangles) {
            t.render(g, pipeline);
        }
    }
}

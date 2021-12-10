package mepco.ca.util.matrix;

import mepco.ca.arcade.engine3d.Actor3D;
import mepco.ca.util.draw.Draw;
import mepco.ca.util.draw.Pipeline3D;
import mepco.ca.util.draw.Texture;

import java.awt.*;
import java.awt.geom.Point2D;

public class Triangle3D implements Actor3D {
    public final Point3D p1, p2, p3;
    public final Texture texture;

    public Triangle3D(Point3D p1, Point3D p2, Point3D p3, Texture texture) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.texture = texture;
    }

    @Override
    public void render(Graphics2D g, Pipeline3D pipeline) {
        if (texture != null) {
            Color oldColor = g.getColor();

            Point2D.Double r1 = pipeline.transform(p1);
            Point2D.Double r2 = pipeline.transform(p1);
            Point2D.Double r3 = pipeline.transform(p1);

            boolean isFrontFacing = isCCW(r1, r2, r3) >= 0;
            Color lineColor = isFrontFacing ? texture.frontLineColor : texture.backLineColor;
            Color fillColor = isFrontFacing ? texture.frontFillColor : texture.backFillColor;

            if (fillColor != null) {
                g.setColor(fillColor);
                Draw.fillPoly(g, r1, r2, r3);
            }

            if (lineColor != null) {
                g.setColor(lineColor);
                Draw.drawPoly(g, r1, r2, r3);
            }
            g.setColor(oldColor);
        }
    }


    static int isCCW(Point2D.Double p0, Point2D.Double p1, Point2D.Double p2) {
        final double dx1 = p1.getX() - p0.getX();
        final double dx2 = p2.getX() - p0.getX();
        final double dy1 = p1.getY() - p0.getY();
        final double dy2 = p2.getY() - p0.getY();

        if (dy1 * dx2 > dy2 * dx1) return -1;
        if (dx1 * dy2 > dy1 * dx2) return 1;
        if ((dx1 * dx2 < 0) || (dy1 * dy2 < 0)) return 1;
        if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)) return -1;
        return 0;
    }
}

package mepco.ca.util.draw;

import mepco.ca.util.matrix.Matrix;
import mepco.ca.util.matrix.Matrix4D;
import mepco.ca.util.matrix.Point3D;
import mepco.ca.util.matrix.Vector4D;

import java.awt.geom.Point2D;

public class Pipeline3D {
    public Matrix4D worldToCamera;
    public Matrix4D projection;
    public double screenW, screenH;

    public Pipeline3D(
            Matrix4D worldToCamera,
            Matrix4D projection,
            double screenW,
            double screenH
    ) {
        this.worldToCamera = worldToCamera;
        this.projection = projection;
        this.screenW = screenW;
        this.screenH = screenH;
    }

    public Point2D.Double transform(Point3D pWorldSpace) {
        final Vector4D pCameraSpace = toCameraSpace(pWorldSpace);
        final Vector4D pHomogeneousSpace = toHomogeneousCoordinates(pCameraSpace);
        final Vector4D pHomogeneousClippingSpace = toHomogeneousClippingSpace(pHomogeneousSpace);
        final Vector4D pNdcSpace = toNdcSpace(pHomogeneousClippingSpace);
        final Vector4D pViewportSpace = toViewportTransform(pNdcSpace);
        return toRasterSpace(pViewportSpace);
    }

    Vector4D toCameraSpace(Point3D pWorldSpace) {
        return new Vector4D(worldToCamera.transform(pWorldSpace));
    }

    Vector4D toHomogeneousCoordinates(Vector4D pCameraSpace) {
        return pCameraSpace;    // TODO: Change;
    }

    Vector4D toHomogeneousClippingSpace(Vector4D pHomogeneousCoordinates) {
        return projection.transform(pHomogeneousCoordinates);
    }

    Vector4D toNdcSpace(Vector4D pHomogeneousClippingSpace) {
        return pHomogeneousClippingSpace;   // TODO: Look into
    }

    Vector4D toViewportTransform(Vector4D pClipSpace) {
        return new Vector4D(
                pClipSpace.getX()/pClipSpace.getW(),
                pClipSpace.getY()/pClipSpace.getW(),
                pClipSpace.getZ()/pClipSpace.getW(),
                1D
        );
    }

    Point2D.Double toRasterSpace(Vector4D p) {
        return new Point2D.Double(
                (p.getX() + 1D) * 0.5D * (screenW-1),
                (1 - (p.getY() + 1) * 0.5D) * (screenH - 1)
        );
    }

}

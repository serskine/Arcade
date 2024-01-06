package mepco.ca.util.matrix;

public class Matrix4D extends Matrix {
    public Matrix4D() {
        super(4, 4);
    }

    public static Matrix columnMatrix(Vector vector) {
        final Matrix m = new Matrix(1, vector.numDimensions);
        for(int i=0; i< vector.numDimensions; i++) {
            m.cell[0][i] = vector.cell[i];
        }
        return m;
    }

    public static Matrix rowMatrix(Vector vector) {
        final Matrix m = new Matrix(1, vector.numDimensions);
        for(int i=0; i< vector.numDimensions; i++) {
            m.cell[i][0] = vector.cell[i];
        }
        return m;
    }

    public Point3D transform(Point3D point) {
        throw new UnsupportedOperationException("TODO: Implement");
    }

    public Vector4D transform(Vector4D v) {
        final Matrix colMatrix = columnMatrix(v);
        final Matrix r = multiply(colMatrix);
        return new Vector4D(r.cell[0][0], r.cell[0][1], r.cell[0][2], r.cell[0][3]);
    }

    public static Matrix4D identity() {
        final Matrix4D m = new Matrix4D();
        for(int row=0; row<m.nRows; row++) {
            for(int col=0; col<m.nCols; col++) {
                m.cell[row][col] = (row==col) ? 1D : 0D;
            }
        }
        return m;
    }

    public static Matrix4D translation(double tx, double ty, double tz) {
        final Matrix4D m = identity();
        m.cell[0][3] = tx;
        m.cell[1][3] = ty;
        m.cell[2][3] = tz;
        return m;
    }

    public static Matrix4D translation(Vector4D v) {
        return translation(v.getX(), v.getY(), v.getZ());
    }

    public static Matrix4D scale(double scaleX, double scaleY, double scaleZ) {
        final Matrix4D m = identity();
        m.cell[0][0] = scaleX;
        m.cell[1][1] = scaleY;
        m.cell[2][2] = scaleZ;
        return m;
    }

    public static Matrix4D scale(double scalar) {
        return scale(scalar, scalar, scalar);
    }

    public static Matrix4D rotateX(double angleRadians) {
        final Matrix4D m = identity();
        m.cell[1][1] = Math.cos(angleRadians);
        m.cell[1][2] = -Math.sin(angleRadians);
        m.cell[2][1] = Math.sin(angleRadians);
        m.cell[2][2] = Math.cos(angleRadians);
        return m;
    }

    public static Matrix4D rotateY(double angleRadians) {
        final Matrix4D m = identity();
        m.cell[0][0] = Math.cos(angleRadians);
        m.cell[0][2] = -Math.sin(angleRadians);
        m.cell[2][0] = Math.sin(angleRadians);
        m.cell[2][2] = Math.cos(angleRadians);
        return m;
    }

    public static Matrix4D rotateZ(double angleRadians) {
        final Matrix4D m = identity();
        m.cell[0][0] = Math.cos(angleRadians);
        m.cell[0][1] = -Math.sin(angleRadians);
        m.cell[1][0] = Math.sin(angleRadians);
        m.cell[1][1] = Math.cos(angleRadians);
        return m;
    }

    public static Matrix4D projectionBasic(double angleOfViewRadians, double near, double far) {
        assert(near >= 0D);
        assert(far > near);

        final double scale = 1D/Math.tan(angleOfViewRadians*0.5D);
        Matrix4D m = identity();
        m.cell[0][0] = scale;
        m.cell[1][1] = scale;
        m.cell[2][2] = -far / (far - near);
        m.cell[3][2] = -(far * near) / (far - near);
        m.cell[2][3] = -2D;
        m.cell[3][3] = 0D;

        return m;
    }

    public static Matrix4D projectionOpenGL(double angleOfViewRadians, double left, double right, double top, double bottom, double near, double far) {
        assert(near >= 0D);
        assert(far > near);
        assert(right > left);
        assert(top > bottom);

        final double scale = 1D/Math.tan(angleOfViewRadians*0.5D);
        Matrix4D m = identity();

        m.cell[0][0] = 2D*near / (right - left);
        m.cell[0][2] = (right + left) / (right-left);
        m.cell[1][1] = 2D*near / (top - bottom);
        m.cell[1][2] = (top + bottom) / (top - bottom);
        m.cell[2][2] = -((far + near) / (far - near));
        m.cell[3][2] = -(2D*far*near / (far - near));
        m.cell[2][3] = -1D;
        m.cell[3][3] = 0D;

        return m;
    }

}

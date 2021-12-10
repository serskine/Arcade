package mepco.ca.util.matrix;

public class Vector4D extends Vector {
    public Vector4D() {
        super(4);
    }

    public Vector4D(Point3D point) {
        super(point.getX(), point.getY(), point.getY(), 1D);
    }

    public Vector4D(double x, double y, double z) {
        this(x, y, z, 1D);
    }
    public Vector4D(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public double getX()    {   return cell[0];     }
    public double getY()    {   return cell[1];     }
    public double getZ()    {   return cell[2];     }
    public double getW()    {   return cell[3];     }

    public void setX(double x)  {   cell[0] = x;    }
    public void setY(double y)  {   cell[1] = y;    }
    public void setZ(double z)  {   cell[2] = z;    }
    public void setW(double w)  {   cell[3] = w;    }

}

package mepco.ca.util.matrix;

public class Vector {
    public final int numDimensions;
    public final double[] cell;
    public Vector(final int numDimensions) {
        assert(numDimensions > 0);
        this.numDimensions = numDimensions;
        this.cell = new double[numDimensions];
    }
    public Vector(double... elem) {
        assert(elem != null);
        assert(elem.length > 0);
        this.numDimensions = elem.length;
        cell = elem;
    }

    public double dotProduct(Vector v2) {
        assert(numDimensions == v2.numDimensions);
        double product = 0D;
        for(int i=0; i<numDimensions; i++) {
            product += cell[i] * v2.cell[i];
        }
        return product;
    }

    public double length() {
        return dotProduct(this);
    }

    public Vector copy() {
        final Vector vNew = new Vector(numDimensions);
        for(int i=0; i<numDimensions; i++) {
            vNew.cell[i] = cell[i];
        }
        return vNew;
    }

    public Vector add(Vector... v) {
        final Vector vNew = copy();
        assert(v != null);
        for(int i=0; i<v.length; i++) {
            assert(numDimensions == v[i].numDimensions);
            for(int dimension=0; dimension<numDimensions; dimension++) {
                vNew.cell[dimension] += v[i].cell[dimension];
            }
        }
        return vNew;
    }


}

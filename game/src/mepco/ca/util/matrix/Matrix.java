package mepco.ca.util.matrix;

public class Matrix {
    public final int nCols, nRows;
    public final double[][] cell;

    public static Matrix identity(int numDimensions) {
        final Matrix m = new Matrix(numDimensions, numDimensions);
        for(int row=0; row<numDimensions; row++) {
            for(int col=0; col<numDimensions; col++) {
                m.cell[row][col] = (row==col) ? 1D : 0D;
            }
        }
        return m;
    }

    public Matrix(final int nCols, final int nRows) {
        assert(nCols>1);
        assert(nRows>1);

        this.nCols = nCols;
        this.nRows = nRows;
        cell = new double[nCols][nRows];
    }

    public Vector getRow(int rowIndex) {
        final Vector v = new Vector(nCols);
        for(int i=0; i<nCols; i++) {
            v.cell[i] = cell[rowIndex][i];
        }
        return v;
    }

    public Vector getCol(int colIndex) {
        final Vector v = new Vector(nRows);
        for(int i=0; i<nRows; i++) {
            v.cell[i] = cell[i][colIndex];
        }
        return v;
    }

    public Matrix copy() {
        final Matrix m = new Matrix(nRows, nCols);
        for(int row=0; row<nRows; row++) {
            for(int col=0; col<nCols; col++) {
                m.cell[row][col] = cell[row][col];
            }
        }
        return m;
    }

    public Matrix multiply(Matrix m) {
        assert(m != null);
        assert(m.nRows == nCols);

        final Matrix r = new Matrix(nRows, m.nCols);
        for(int row = 0; row<r.nRows; row++) {
            for(int col=0; col<r.nCols; col++) {
                final Vector v1 = getRow(row);
                final Vector v2 = m.getCol(col);
                m.cell[row][col] = v1.dotProduct(v2);
            }
        }
        return r;
    }

    public Matrix add(Matrix m) {
        assert(m != null);
        assert(nRows == m.nRows);
        assert(nCols == m.nCols);

        final Matrix r = new Matrix(nRows, nCols);

        for(int row=0; row<nRows; row++) {
            for(int col=0; col<nCols; col++) {
                r.cell[row][col] = cell[row][col] + m.cell[row][col];
            }
        }

        return r;
    }


}

package mepco.ca.util.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class World<T> {


    protected T defaultValue;

    public World(T defaultValue, Dimension... dimensions) {
        setDefaultValue(defaultValue);
        setDimensions(dimensions);
    }

    public abstract void setDimensions(Dimension[] dimensions);


    public abstract void validateDimensions(Dimension[] dimensions);


    public abstract Dimension[] getDimensions();

    /**
     * This will return the minimum value of the dimension inclusive. If the size if infinite then this will return null.
     * If the size is finite then the minimum value will be 0, to keep it within the positive coordinate plane.
     * @param dimension
     * @return
     */
    public Integer getMin(int dimension) {
        validateDimension(dimension);
        if (getDimensions()[dimension].isFinite()) {
            return ((FiniteDimension) getDimensions()[dimension]).getMin();
        } else {
            return null;
        }
    }

    /**
     * This will return the size of the dimension. If the size if infinite then this will return null.
     * @param dimension
     * @return
     */
    public Integer getSize(int dimension) {
        validateDimension(dimension);
        if (getDimensions()[dimension].isFinite()) {
            return ((FiniteDimension) getDimensions()[dimension]).getSize();
        } else {
            return null;
        }
    }

    /**
     * This will return the maximum value of the dimension inclusive. If the size if infinite then this will return null.
     * If the size of finite then it will return the size of the dimension - -1 to keep it within the positive coordinate plane.
     * @param dimension
     * @return
     */
    public Integer getMax(int dimension) {
        validateDimension(dimension);
        if (getDimensions()[dimension].isFinite()) {
            return ((FiniteDimension) getDimensions()[dimension]).getMax();
        } else {
            return null;
        }
    }

    /**
     * This will return the number of dimensions a coordinate is expected to have.
     * @return
     */
    public final int getNumDimensions() {
        return getDimensions().length;
    }

    /**
     * This will validate the dimension specified exists.
     * @param dimension
     */
    public void validateDimension(int dimension) {
        if (dimension < 0 || dimension >= getNumDimensions()) {
            throw new RuntimeException("Dimension " + dimension + " does not exist. There are " + getNumDimensions() + " dimensions starting at index 0.");
        }
    }

    public final void validateCoordinate(int... coordinate) {
        if (coordinate.length != getNumDimensions()) {
            throw new RuntimeException("A coordinate must specify exactly " + getNumDimensions() + " dimensions.");
        }
        for(int d=0; d<coordinate.length; d++) {
            final Integer min = getMin(d);
            final Integer max = getMax(d);
            if (min != null && coordinate[d] < min) {
                throw new RuntimeException("Invalid coordinate dimension [" + d + "] : " + coordinate[d] + " < " + min + ". Below minimum.");
            }
            if (max != null && coordinate[d] > max) {
                throw new RuntimeException("Invalid coordinate dimension [" + d + "] : " + coordinate[d] + " > " + max + ". Above maximum.");
            }
        }
    }

    /**
     * This is a simple check to see if a coordinate is valid or not.
     * @param coordinate
     * @return true if the coordinate exists in the world and false if it is invalid.
     */
    public boolean isValidCoordinate(int... coordinate) {
        try {
            validateCoordinate(coordinate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return the default value expected for all coordinates currently not set to a value
     */
    public T getDefaultValue() {
        return defaultValue;
    }

    /**
     * This will set the value of a coordinate so that it no longer uses the default value
     * @param defaultValue
     */
    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public abstract Location<T> location(final int... coordinate);

    public final List<int[]> getAdjacent(int... coordinate) {
        validateCoordinate(coordinate);
        List<int[]> adjacent = new ArrayList<>();

        for(int i=0; i<getNumDimensions(); i++) {
            Integer min = getMin(i);
            Integer max = getMax(i);

            // Get one step down on this dimension
            int[] newP1 = Arrays.copyOf(coordinate, coordinate.length);
            newP1[i] = newP1[i] - 1;
            if (min == null || newP1[i] >= min) {
                adjacent.add(newP1);
            }

            // Get one step down up this dimension
            int[] newP2 = Arrays.copyOf(coordinate, coordinate.length);
            newP2[i] = newP2[i] + 1;
            if (max == null || newP2[i] <= max) {
                adjacent.add(newP2);
            }

        }

        return adjacent;
    }




    public final List<int[]> getDeltas(int numDimensions) {
        final List<int[]> deltas = new ArrayList<>();
        final int[] c = new int[numDimensions];
        for(int i=0; i<numDimensions; i++) {
            c[i] = -1;
        }
        int[] v = Arrays.copyOf(c, c.length);
        deltas.add(v);

        int x=0;
        while(x<numDimensions) {
            if (c[x]==1) {
                c[x] = -1;
                x++;
            } else {
                c[x]++;
                x = 0;
                v = Arrays.copyOf(c, c.length);
                deltas.add(v);
            }
        }
        return deltas;
    }

    public final List<int[]> getNeighbours(int... coordinate) {
        validateCoordinate(coordinate);
        final List<int[]> deltas = getDeltas(coordinate.length);
        final List<int[]> neighbours = new ArrayList<>();
        for(int[] d : deltas) {
            int[] newC = new int[coordinate.length];
            boolean isDifferent = false;
            for(int i=0; i<newC.length; i++) {
                newC[i] = coordinate[i] + d[i];
                isDifferent = isDifferent || d[i]!=0;
            }
            if (isDifferent) {
                neighbours.add(newC);
            }
        }

        return neighbours.stream()
                .filter(c -> isValidCoordinate(c)).collect(Collectors.toList());
    }

    public abstract World<T> copy();


    public static final void validateCoordinatesEqual(int[] c1, int[] c2) {
        if (c1==null) {
            if (c2!=null) {
                throw new RuntimeException("left coordinate is null but right coordinate is not null.");
            }
        } else if (c2==null) {
            throw new RuntimeException("left coordinate is not null but right coordinate is null.");
        } else if (c1.length != c2.length) {
            throw new RuntimeException("The number of dimensions for each coordinate is different. (" + c1.length + " != " + c2.length + ")");
        } else {
            for(int i=0; i<c1.length; i++) {
                if (c1[i] != c2[i]) {
                    throw new RuntimeException("Dimension [" + i + "] is not the same! (" + c1[i] + " != " + c2[i] + ")");
                }
            }
        }
    }

    public static final boolean isEqual(int[] c1, int [] c2) {
        try {
            validateCoordinatesEqual(c1, c2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static final boolean contains(final Collection<int[]> collection, int... coordinate) {
        return collection.stream().filter(c -> isEqual(coordinate, c)).findAny().isPresent();
    }


    public static final Dimension[] buildDimensions(Integer... sizes) {
        Dimension[] d = new Dimension[sizes.length];
        for(int i=0; i<d.length; i++) {

            if (sizes[i]==null) {
                d[i] = new InfiniteDimension();
            } else {
                d[i] = new FiniteDimension(sizes[i]);
            }
        }
        return d;
    }

    public static final InfiniteDimension[] buildInfiniteDimensions(int numDimensions) {
        final InfiniteDimension[] d = new InfiniteDimension[numDimensions];
        for(int i=0; i<d.length; i++) {
            d[i] = new InfiniteDimension();
        }
        return d;
    }
}

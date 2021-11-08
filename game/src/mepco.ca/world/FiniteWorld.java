package mepco.ca.world;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FiniteWorld<T> extends World<T> {


    public interface FiniteWorldLocation<T> extends Location<T> {
        int getIndex();
    }

    private Object[] items;
    protected FiniteDimension[] dimensions;
    private int[] dimensionMods;

    public FiniteWorld(T defaultValue, Integer... sizes) {
        this(defaultValue, buildDimensions(sizes));
    }
    public FiniteWorld(T defaultValue, Dimension... dimensions) {
        super(defaultValue, dimensions);
    }

    static final int calcSize(int... dimensions) {
        int sum = 1;
        for(int i=0; i<dimensions.length; i++) {
            sum *= dimensions[i];
        }
        return sum;
    }

    /**
     * This will return the sizes of all dimensions. If a dimension has no bounds then it's index is null.
     * @return
     */
    public final Dimension[] getDimensions() {
        return this.dimensions;
    }

    FiniteDimension[] toFiniteDimensions(final Dimension[] d) {
        validateDimensions(d);
        final int numDimensions = d.length;
        final FiniteDimension[] finiteDimensions = new FiniteDimension[numDimensions];
        for(int i=0; i<numDimensions; i++) {
            finiteDimensions[i] = (FiniteDimension) d[i];
        }
        return finiteDimensions;
    }

    @Override
    public void setDimensions(final Dimension[] dimensions) {
        validateDimensions(dimensions);
        this.dimensions = toFiniteDimensions(dimensions);
        this.items = createDefaultMap(getDefaultValue(), this.dimensions);
        this.dimensionMods = calcDimModifiers(this.dimensions);
    }

    @Override
    public void validateDimensions(Dimension[] dimensions) {
        if (dimensions==null) {
            throw new RuntimeException("The dimensions array can not be null.");
        }

        if (dimensions.length < 1) {
            throw new RuntimeException("There must be at least one dimension.");
        }

        for(int i=0; i<dimensions.length; i++) {
            if (!dimensions[i].isFinite()) {
                throw new RuntimeException("Dimension " + i + " is expected to be finite.");
            }
            final FiniteDimension d = (FiniteDimension) dimensions[i];
            if (d.getSize() < 1) {
                throw new RuntimeException("Dimension " + i + " must have a size of at least 1");
            }
        }
    }

    public void validateIndex(int index) {
        if (index < 0) {
            throw new RuntimeException("The index is < 0");
        }
        if (index >= items.length) {
            throw new RuntimeException("The index is >= " + items.length);
        }
    }

    Object[] createDefaultMap(T defaultValue, FiniteDimension[] dimensions) {
        final int size = calcSize(extractFiniteDimensionSizes(dimensions));
        final Object[] items = new Object[size];
        for(int i=0; i<items.length; i++) {
            items[i] = defaultValue;
        }
        return items;
    }

    int toIndex(int... coordinate) {
        validateCoordinate(coordinate);
        int sum = 0;
        for(int i=0; i<coordinate.length; i++) {
            int d = dimensions.length-1-i;
            int offset = getDimensionMod(d);
            sum += coordinate[d] * offset;
        }
        return sum;
    }

    int[] toCoordinate(int index) {
        validateIndex(index);
        final int c[] = new int[dimensions.length];

        for(int i=dimensions.length-1; i>=0; i--) {
            int s = getSize(i);
            c[i] = index % s;
            index = index / s;
        }

        return c;
    }


    int getDimensionMod(int dim) {
        validateDimension(dim);
        return dimensionMods[dim];
    }

    static int[] calcDimModifiers(FiniteDimension... dimensions) {
        int[] o = new int[dimensions.length];
        o[o.length-1] = 1;
        for(int i=o.length-2; i>=0; i--) {
            o[i] = o[i+1] * dimensions[i+1].getSize();
        }
        return o;
    }

    public Location<T> locationByIndex(int i) {
        validateIndex(i);
        return location(toCoordinate(i));
    }

    /**
     * This is how you interact with each location in the world.
     * @param coordinate specifies which location to use
     * @return the {@link Location <T>} interface to handle it.
     */
    public Location<T> location(final int... coordinate) {
        validateCoordinate(coordinate);
        final int index = toIndex(coordinate);
        return new FiniteWorldLocation<T>() {

            @Override
            public T getValue() {
                return (T) items[index];
            }

            @Override
            public void set(T value) {
                items[index] = value;
            }

            @Override
            public int getIndex() {
                return toIndex(coordinate);
            }

            @Override
            public int[] getCoordinate() {
                return coordinate;
            }
        };
    }

    public final Iterator<int[]> getCoordinateItr() {
        return new Iterator<int[]>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return (i < items.length);
            }

            @Override
            public int[] next() {
                final int[] v = toCoordinate(i);
                i++;
                return v;
            }
        };
    }

    public final Stream<int[]> getCoordinateStream() {
        Iterator<int[]> itr = getCoordinateItr();
        Spliterator<int[]> sitr = Spliterators.spliteratorUnknownSize(itr, 0);
        return StreamSupport.stream(sitr, false);
    }

    public final Stream<Location<T>> getLocationsStream() {
        return getCoordinateStream().map(c -> location(c));
    }


    public static final String coordinateString(int... a) {

        final StringBuilder sb = new StringBuilder();
        if (a==null) {
            sb.append("null");
        } else {
            sb.append("[");
            for (int i = 0; i < a.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(a[i]);
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public World<T> copy() {
        final FiniteWorld<T> copy = new FiniteWorld<>(this.defaultValue, this.dimensions);
        copy.getLocationsStream().forEach(location -> {
            location.set(this.location(location.getCoordinate()).getValue());
        });
        return copy;
    }


    public static final int[] extractFiniteDimensionSizes(FiniteDimension... d) {
        int[] s = new int[d.length];
        for(int i=0; i<d.length; i++) {
            s[i] = d[i].getSize();
        }
        return s;
    }


}






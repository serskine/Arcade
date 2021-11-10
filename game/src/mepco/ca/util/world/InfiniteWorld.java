package mepco.ca.util.world;

import java.util.*;

public class InfiniteWorld<T> extends World<T> {

    private Map<String, T> itemsMap;
    private InfiniteDimension[] dimensions;

    public interface InfiniteLocation<T> extends Location<T> {
        String getKey();
    }

    public InfiniteWorld(T defaultValue, int numDimensions) {
        this(defaultValue, buildInfiniteDimensions(numDimensions));
    }

    public InfiniteWorld(T defaultValue, InfiniteDimension... dimensions) {
        super(defaultValue, dimensions);
        setDefaultValue(defaultValue);
        setDimensions(dimensions);
    }


    @Override
    public void setDimensions(Dimension[] dimensions) {
        validateDimensions(dimensions);
        this.dimensions = toInfiniteDimensions(dimensions);
        itemsMap = new HashMap<>();
    }

    @Override
    public void validateDimensions(Dimension[] dimensions) {
        if (dimensions==null) {
            throw new RuntimeException("The dimensions array can not be null.");
        }
        if (dimensions.length==0) {
            throw new RuntimeException("There must be at least one dimension.");
        }
        for(int i=0; i<dimensions.length; i++) {
            if (dimensions[i].isFinite()) {
                throw new RuntimeException("Expected dimension " + i + " to be infinite.");
            }
        }
    }

    @Override
    public InfiniteDimension[] getDimensions() {
        return this.dimensions;
    }

    @Override
    public Integer getMin(int dimension) {
        final Integer s = getSize(dimension);
        if (s==null) {
            return null;
        } else {
            return 0;
        }
    }

    @Override
    public void validateDimension(int dimension) {
        if (dimension < 0 || dimension >= getNumDimensions()) {
            throw new RuntimeException("Dimension " + dimension + " does not exist. There are " + getNumDimensions() + " dimensions starting at index 0.");
        }
    }

    @Override
    public T getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public InfiniteLocation<T> location(final int... coordinate) {
        final String key = toCoordinateString(coordinate);
        return new InfiniteLocation<T>() {

            @Override
            public String getKey() {
                return toCoordinateString(coordinate);
            }

            @Override
            public T getValue() {
                return itemsMap.getOrDefault(key, getDefaultValue());
            }

            @Override
            public void set(T value) {
                itemsMap.put(key, value);
            }

            @Override
            public int[] getCoordinate() {
                return coordinate;
            }
        };
    }



    @Override
    public World copy() {
        final InfiniteWorld<T> newWorld = new InfiniteWorld<>(getDefaultValue(), (InfiniteDimension[]) getDimensions());
        newWorld.itemsMap.putAll(itemsMap);
        return newWorld;
    }

    Map<String, T> createDefaultMap(T defaultValue, Integer[] dimensions) {
        return new HashMap<>();
    }

    final String toCoordinateString(int... coordinate) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean isFirst = true;
        for(int i : coordinate) {
            if (!isFirst) {
                sb.append(", ");
            }
            isFirst = false;
            sb.append(i);
        }
        sb.append("}");
        return sb.toString();
    }


    InfiniteDimension[] toInfiniteDimensions(final Dimension[] d) {
        validateDimensions(d);
        final int numDimensions = d.length;
        final InfiniteDimension[] finiteDimensions = new InfiniteDimension[numDimensions];
        for(int i=0; i<numDimensions; i++) {
            finiteDimensions[i] = (InfiniteDimension) d[i];
        }
        return finiteDimensions;
    }
}

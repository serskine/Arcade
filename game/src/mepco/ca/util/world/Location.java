package mepco.ca.util.world;

public interface Location<T> {
    T getValue();
    void set(final T value);
    int[] getCoordinate();
}

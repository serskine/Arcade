package mepco.ca.world;

public interface Location<T> {
    T getValue();
    void set(final T value);
    int[] getCoordinate();
}

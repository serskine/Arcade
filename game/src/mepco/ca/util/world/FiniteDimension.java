package mepco.ca.util.world;

public class FiniteDimension extends Dimension {
    private boolean isWrapped = false;
    private int min = 0;
    private int size = 1;

    public FiniteDimension() {
        this(false, 0, 1);
    }

    public FiniteDimension(int size) {
        this(false, 0, size);
    }

    public FiniteDimension(boolean isWrapped, int size) {
        this(isWrapped, 0, size);
    }

    public FiniteDimension(boolean isWrapped, int min, int size) {
        this.isWrapped = isWrapped;
        this.min = min;
        this.size = size;
    }

    public boolean isWrapped() {
        return isWrapped;
    }

    public void setWrapped(boolean wrapped) {
        isWrapped = wrapped;
    }

    public int getMin() {
        return min;
    }

    public final int getMax() {
        return getMin() + getSize() - 1;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < 1) {
            throw new RuntimeException("Size must be at least one unit.");
        }
        this.size = size;
    }

    @Override
    public boolean isFinite() {
        return true;
    }
}

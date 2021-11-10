package mepco.ca.arcade;

public class BindingState {
    public final ArcadeButton button;
    public final int code;

    public BindingState(int code, ArcadeButton button) {
        this.button = button;
        this.code = code;
    }
}

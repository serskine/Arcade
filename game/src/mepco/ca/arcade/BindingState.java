package mepco.ca.arcade;

public class BindingState {
    public final ArcadeButton button;
    public final int code;

    public BindingState(ArcadeButton button, int code) {
        this.button = button;
        this.code = code;
    }
}

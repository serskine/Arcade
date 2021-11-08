package mepco.ca.arcade;

public class Binding {
    public int playerId;
    public Button button;
    public Character character;

    public Binding(int playerId, Character character, Button button) {
        this.playerId = playerId;
        this.character = character;
        this.button = button;
    }
}

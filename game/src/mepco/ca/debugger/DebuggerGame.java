package mepco.ca.debugger;

import mepco.ca.arcade.ArcadeGame;
import mepco.ca.arcade.ArcadeRootActor;

public class DebuggerGame extends ArcadeGame {

    public DebuggerGame() {
        super();
    }

    @Override
    public ArcadeRootActor createRootActor() {
        return new DebuggerRootActor();
    }

    public static void main(String args[]){
        new DebuggerGame();
    }
}

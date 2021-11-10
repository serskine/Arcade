package mepco.ca.dodge;

import mepco.ca.arcade.ArcadeGame;
import mepco.ca.arcade.ArcadeRootActor;

public class DodgeGame extends ArcadeGame {

    public DodgeGame() {
        super();
    }

    @Override
    public ArcadeRootActor createRootActor() {
        return new DodgeRootActor();
    }

    public static void main(String args[]){
        new DodgeGame();
    }


}


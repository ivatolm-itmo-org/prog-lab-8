package server.games;

import server.game.Game;
import server.game.Lobby;

/**
 * Class representing 'Monopoly' game.
 */
public class Monopoly extends Game {

    public Monopoly(Lobby lobby) {
        super(lobby);
    }

    public static Integer getRequiredLobbySize() {
        return 4;
    }

}

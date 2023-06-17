package server.game;

/**
 * Class responsible for leading a game.
 */
public abstract class Game implements GameInfo {

    Lobby lobby;

    protected Game(Lobby lobby) {
        this.lobby = lobby;
    }

}

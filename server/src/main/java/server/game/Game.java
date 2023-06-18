package server.game;

/**
 * Class responsible for leading a game.
 */
public abstract class Game implements GameInfo {

    protected Lobby lobby;

    protected Game(Lobby lobby) {
        this.lobby = lobby;
    }

}

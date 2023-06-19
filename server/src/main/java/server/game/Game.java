package server.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Class responsible for leading a game.
 */
public abstract class Game implements GameInfo {

    protected Lobby lobby;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    protected GameState state;

    protected Game(Lobby lobby) {
        this.lobby = lobby;
        this.state = GameState.Staring;
    }

    public abstract void update();

}

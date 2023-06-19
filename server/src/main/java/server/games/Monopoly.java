package server.games;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.game.Game;
import server.game.GameState;
import server.game.Lobby;

/**
 * Class representing 'Monopoly' game.
 */
public class Monopoly extends Game {

    private static final Logger logger = LoggerFactory.getLogger("Monopoly");

    public Monopoly(Lobby lobby) {
        super(lobby);
    }

    public static Integer getRequiredLobbySize() {
        return 4;
    }

    @Override
    public void update() {
        logger.trace("Current state: {}", getState());
        switch (getState()) {
            case Staring:
                handleStartingState();
                break;
            case Running:
                break;
            case Halting:
                break;
            case Waiting:
                break;
        }
    }

    void handleStartingState() {
        logger.debug("Player count / Required player count ({}/{})", lobby.getPlayerCount(),
                lobby.getRequiredPlayerCount());
        if (lobby.getPlayerCount().equals(lobby.getRequiredPlayerCount())) {
            setState(GameState.Running);
            logger.debug("Game state set to {}", GameState.Running);
            logger.info("Game started");
        }
    }

}

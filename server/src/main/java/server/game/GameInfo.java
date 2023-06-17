package server.game;

import javax.naming.OperationNotSupportedException;

/**
 * Class representing some information about a game.
 */
public interface GameInfo {

    static Integer getRequiredLobbySize() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

}

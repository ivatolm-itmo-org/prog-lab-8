package server.game;

import java.util.LinkedList;

/**
 * Class responsible for providing consistent interface
 * of players.
 */
public class Lobby {

    private LinkedList<Player> players;

    private Integer size;

    public Lobby(Integer size) {
        this.size = size;
    }

    public Boolean addPlayer(Player player) {
        if (players.size() >= size)
            return false;

        players.add(player);
        return true;
    }

    public void removePlayer() {

    }

    public Integer getRequiredPlayerCount() {
        return size;
    }

    public Integer getPlayerCount() {
        return players.size();
    }

}

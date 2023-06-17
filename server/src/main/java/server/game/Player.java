package server.game;

import java.nio.channels.SocketChannel;

import lombok.Getter;

/**
 * Class representing human player.
 */
public class Player {

    @Getter
    private SocketChannel address;

    public Player(SocketChannel address) {
        this.address = address;
    }

}

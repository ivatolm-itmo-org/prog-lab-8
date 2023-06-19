package server.runner;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.game.Game;
import server.game.Lobby;
import server.game.Player;

/**
 * Class for handling incoming events.
 */
public class EventHandler {

    private static final Logger logger = LoggerFactory.getLogger("EventHandler");

    private Game game;

    private Lobby lobby;

    private ServerSocketChannel socket;

    public EventHandler(Game game, Lobby lobby, ServerSocketChannel socket) {
        this.game = game;
        this.lobby = lobby;
        this.socket = socket;
    }

    public void handleNewPlayer() {
        SocketChannel client;
        try {
            client = socket.accept();
        } catch (IOException e) {
            logger.warn("Cannot accept new player: {}", e);
            return;
        }

        Player player = new Player(client);

        Boolean isAdded = lobby.addPlayer(player);
        if (isAdded) {
            try {
                logger.info("New player connected: {}", client.getRemoteAddress().toString());
            } catch (IOException e) {
                logger.debug("Cannot get client's remote address: {}", e);
                logger.info("New player connected");
            }
        } else {
            try {
                logger.info("New player rejected. Lobby is full");
                client.close();
            } catch (IOException e) {
                logger.debug("Cannot close client's socket: {}", e);
            }
        }

        game.update();
    }

    public void handleNewPlayerEvent() {

    }

}

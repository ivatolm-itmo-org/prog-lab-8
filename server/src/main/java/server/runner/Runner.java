package server.runner;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.game.Game;
import server.game.Lobby;
import server.game.Player;
import server.games.Monopoly;

/**
 * Class responsible for running the game.
 */
public class Runner {

    private static final Logger logger = LoggerFactory.getLogger("Runner");

    private Game game;

    private Lobby lobby;

    private Selector selector;

    private ServerSocketChannel socket;

    public Runner(RunnerConfig config) throws IOException {
        Integer requiredLobbySize = Monopoly.getRequiredLobbySize();
        this.lobby = new Lobby(requiredLobbySize);
        this.game = new Monopoly(lobby);

        ServerSocketChannel socket = ServerSocketChannel.open();
        socket.bind(config.getHostAddress());

        selector = Selector.open();
        socket.configureBlocking(false);
        socket.register(selector, SelectionKey.OP_ACCEPT);

        logger.trace("Setup finished");
    }

    public void run() {
        Integer counterIOFailure = 0;

        while (true) {
            try {
                selector.select();
                counterIOFailure = 0;

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                logger.trace("Selected keys count: {}", selectedKeys.size());

                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        logger.trace("New player connecting...");
                        handleNewPlayer();
                    }

                    iter.remove();
                }
            } catch (IOException e) {
                logger.warn("Cannot finish select: {}", e);

                counterIOFailure += 1;
                logger.debug("IO failure counter: {}", counterIOFailure);

                if (counterIOFailure > 5) {
                    logger.error("Cannot recover from the error. Exiting...");
                    return;
                }
            }
        }
    }

    private void handleNewPlayer() {
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
                client.close();
            } catch (IOException e) {
                logger.debug("Cannot close client's socket: {}", e);
            }
        }
    }

}

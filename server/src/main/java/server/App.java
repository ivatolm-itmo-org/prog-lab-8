package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import server.runner.Runner;
import server.runner.RunnerConfig;

/**
 * Server of the game.
 */
public class App {
    public static void main(String[] args) {
        Thread.currentThread().setName("main");

        if (args.length < 2) {
            System.err.println("Not enough arguments provided");
            return;
        }

        String ip = args[0];
        Integer port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Port must be an integer, you provided: " + args[1]);
            return;
        }

        SocketAddress address = new InetSocketAddress(ip, port);

        RunnerConfig config = new RunnerConfig(address);

        Runner runner;
        try {
            runner = new Runner(config);
        } catch (IOException e) {
            System.err.println("Cannot start game because of socket error");
            return;
        }

        runner.run();
    }
}

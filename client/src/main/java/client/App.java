package client;

/**
 * Client of the game.
 */
public class App {

    public static void main(String[] args) {
        Thread.currentThread().setName("main");

        GUI.launch();
    }

}

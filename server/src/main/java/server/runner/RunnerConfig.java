package server.runner;

import java.net.SocketAddress;

import lombok.Getter;

/**
 * Class representing parsed arguments for the Runner.
 */
public class RunnerConfig {

    @Getter
    private SocketAddress hostAddress;

    public RunnerConfig(SocketAddress address) {
        this.hostAddress = address;
    }

}

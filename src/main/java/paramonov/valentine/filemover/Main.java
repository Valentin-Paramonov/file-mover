package paramonov.valentine.filemover;

import paramonov.valentine.filemover.server.TomcatServer;

import static paramonov.valentine.filemover.env.EnvironmentVariableProvider.getIntEnv;

public class Main {
    public static void main(String[] args) {
        new TomcatServer(getIntEnv("PORT", 8080)).run();
    }
}

package paramonov.valentine.filemover;

import paramonov.valentine.filemover.server.TomcatServer;

import static paramonov.valentine.filemover.EnvironmentVariableProvider.getIntEnv;

public class Main {
    public static void main(String[] args) {
        TomcatServer.runOn(getIntEnv("PORT", 8080));
    }
}

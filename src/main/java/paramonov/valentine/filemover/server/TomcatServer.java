package paramonov.valentine.filemover.server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

public class TomcatServer {
    private final static String contextPath = "";
    private final static String appBase = ".";

    /**
     * Starts the embedded Tomcat server on the provided port
     *
     * @param port the port on which to run the server on
     * @throws ServerStartException if problem with starting the server occurs
     */
    public static void runOn(int port) throws ServerStartException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getHost().setAppBase(appBase);
        try {
            tomcat.addWebapp(contextPath, appBase);
            tomcat.start();
        } catch (ServletException | LifecycleException e) {
            throw new ServerStartException(e);
        }
        tomcat.getServer().await();
    }
}

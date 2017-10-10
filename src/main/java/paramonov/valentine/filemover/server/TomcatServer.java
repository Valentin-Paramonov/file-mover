package paramonov.valentine.filemover.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import paramonov.valentine.filemover.logging.Loggable;

import javax.servlet.ServletException;
import java.io.IOException;

public class TomcatServer implements Loggable {
    private static final String contextPath = "";
    private static final String appBase = ".";
    private final int port;

    /**
     * @param port the port on which to run the server on
     */
    public TomcatServer(int port) {
        this.port = port;
    }

    /**
     * Starts the embedded Tomcat server on the port provided in the constructor
     *
     * @throws ServerStartException if problem with starting the server occurs
     */
    public void run() throws ServerStartException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getHost().setAppBase(appBase);
        try {
            Context context = tomcat.addWebapp(contextPath, appBase);
            getWebXmlToBeLoadedByThe(context);
            tomcat.start();
        } catch (ServletException | LifecycleException | IOException e) {
            throw new ServerStartException(e);
        }
        log("Server started on port " + port);
        tomcat.getServer().await();
    }

    // There doesn't seem to be a way to load the web.xml from JAR,
    // therefore web.xml is first written to disk and then, the context
    // is pointed to it.
    private void getWebXmlToBeLoadedByThe(Context context) throws IOException {
        String webXmlPathOnDisk = new WebXmlExtractor().extractWebXmlFromJar();
        context.setAltDDName(webXmlPathOnDisk);
    }
}

package paramonov.valentine.filemover.mover;

import paramonov.valentine.filemover.context.ContextAttribute;
import paramonov.valentine.filemover.context.ServletConfigWrapper;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Initializes and starts a {@link DirectoryPoller} using the parameters from {@link
 * ServletConfig}
 */
public class DirectoryPollerInitializer extends GenericServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        ServletConfigWrapper config = new ServletConfigWrapper(servletConfig);
        String sourceDirectory = config.initParam("sourceDirectory").orElse("/tmp");
        String destinationDirectory = config
            .initParam("destinationDirectory").orElse("/tmp");
        long pollingInterval = config.initParamLong("pollingInterval").orElse(10L);
        ServletContext servletContext = servletConfig.getServletContext();
        ScheduledExecutorService threadPool = (ScheduledExecutorService) servletContext
            .getAttribute(ContextAttribute.threadPool);
        ProcessedFileLog fileLog = new ProcessedFileLog();
        servletContext.setAttribute(ContextAttribute.fileLog, fileLog);
        DirectoryPoller poller = new DirectoryPoller(threadPool, fileLog);
        threadPool.scheduleAtFixedRate(
            () -> poller.poll(sourceDirectory, destinationDirectory),
            1,
            pollingInterval,
            TimeUnit.SECONDS
        );
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
        throws ServletException, IOException {
    }
}

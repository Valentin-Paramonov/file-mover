package paramonov.valentine.filemover.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Initializes a {@link ScheduledThreadPoolExecutor} of {@value #poolSize} daemon
 * threads with priority {@value #threadPriority} on context startup and stores it as a
 * context attribute under the key {@value ContextAttribute#threadPool}
 */
public class ThreadPoolInitializer implements ServletContextListener {
    private static final int poolSize = 4;
    private static final int threadPriority = 4;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ContextAttribute.threadPool, scheduledThreadPool());
    }

    private Executor scheduledThreadPool() {
        return new ScheduledThreadPoolExecutor(
            poolSize,
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setPriority(threadPriority);
                thread.setDaemon(true);
                return thread;
            }
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ThreadPoolExecutor pool =
            (ThreadPoolExecutor) context.getAttribute(ContextAttribute.threadPool);
        pool.shutdown();
    }
}

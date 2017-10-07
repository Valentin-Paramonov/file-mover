package paramonov.valentine.filemover.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@WebListener
public class ThreadPoolInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ContextAttribute.threadPool, scheduledThreadPool());
    }

    private Executor scheduledThreadPool() {
        return new ScheduledThreadPoolExecutor(
            4,
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setPriority(4);
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

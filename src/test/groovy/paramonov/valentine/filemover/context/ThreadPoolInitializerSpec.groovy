package paramonov.valentine.filemover.context

import spock.lang.Specification

import javax.servlet.ServletContext
import javax.servlet.ServletContextEvent
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor

class ThreadPoolInitializerSpec extends Specification {
    def initializer = new ThreadPoolInitializer()
    def context = Mock(ServletContext)
    def event = Mock(ServletContextEvent) {
        it.servletContext >> context
    }

    def "should create a thread pool on startup and store it in the context"() {
        when: 'context is initialized'
            initializer.contextInitialized(event)
        then: 'thread pool is crated and stored in the context'
            1 * context.setAttribute(
                ContextAttribute.threadPool,
                {
                    it.shutdown()
                    it.class == ScheduledThreadPoolExecutor
                }
            )
    }

    def "should shutdown the thread pool when the context is destroyed"() {
        given:
            def threadPool = Mock(ThreadPoolExecutor)
        when: 'the context is destroyed'
            initializer.contextDestroyed(event)
        then:
            1 * context.getAttribute(ContextAttribute.threadPool) >> threadPool
            1 * threadPool.shutdown()
    }
}

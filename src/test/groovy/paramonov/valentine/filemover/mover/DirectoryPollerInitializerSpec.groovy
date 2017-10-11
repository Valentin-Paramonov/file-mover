package paramonov.valentine.filemover.mover

import paramonov.valentine.filemover.context.ContextAttribute
import spock.lang.Specification

import javax.servlet.ServletConfig
import javax.servlet.ServletContext
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class DirectoryPollerInitializerSpec extends Specification {
    def "should get needed parameters from ServeltConfig and start the poller"() {
        given:
            def initializer = new DirectoryPollerInitializer()
            def threadPool = Mock(ScheduledThreadPoolExecutor)
            def context = Mock(ServletContext) {
                it.getAttribute(ContextAttribute.threadPool) >> threadPool
            }
            def config = Mock(ServletConfig) {
                it.getInitParameter('pollingInterval') >> '666'
                it.getServletContext() >> context
            }
        when:
            initializer.init(config)
        then: 'processed file log attribute created in the context'
            1 * context.setAttribute(ContextAttribute.fileLog, _ as ProcessedFileLog)
        then: 'runnable is scheduled'
            1 * threadPool.scheduleAtFixedRate(
                _ as Runnable,
                _ as Long,
                666L,
                TimeUnit.SECONDS
            )
    }
}

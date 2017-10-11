package paramonov.valentine.filemover.mover

import groovy.json.JsonBuilder
import paramonov.valentine.filemover.context.ContextAttribute
import spock.lang.Specification

import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ProcessedFileListEndpointSpec extends Specification {
    def endpoint = new ProcessedFileListEndpoint()

    def "should write file list to response as JSON"() {
        given:
            def request = Mock(HttpServletRequest)
            def context = Mock(ServletContext)
            def fileLog = Mock(ProcessedFileLog)
            def response = Mock(HttpServletResponse)
            def writer = Mock(PrintWriter)
        when: 'endpoint is called'
            endpoint.doGet(request, response)
        then: 'file list is written as JSON'
            1 * request.getServletContext() >> context
            1 * context.getAttribute(ContextAttribute.fileLog) >> fileLog
            1 * fileLog.processedFiles >> ['a', 'b', 'c']
            1 * response.writer >> writer
            1 * writer.write(new JsonBuilder(['a', 'b', 'c']).toString())
    }
}

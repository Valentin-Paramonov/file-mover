package paramonov.valentine.filemover.context

import spock.lang.Specification

import javax.servlet.ServletConfig

class ServletConfigWrapperSpec extends Specification {
    def config = Mock(ServletConfig)
    def wrapper = new ServletConfigWrapper(config)

    def "should return an optional with the value if parameter exists"() {
        given:
            1 * config.getInitParameter('param') >> 'value'
        expect:
            wrapper.initParam('param').get() == 'value'
    }

    def "should return an empty optional if the parameter doesn't exists"() {
        expect:
            !wrapper.initParam('param').isPresent()
    }

    def "should return a long value of the parameter if it exists"() {
        given:
            1 * config.getInitParameter('param') >> '1337'
        expect:
            wrapper.initParamLong('param').get() == 1337
    }

    def "should return an empty optional if the long parameter doesn't exists"() {
        expect:
            !wrapper.initParamLong('param').isPresent()
    }
}

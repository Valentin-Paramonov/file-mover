package paramonov.valentine.filemover.context;

import javax.servlet.ServletConfig;
import java.util.Optional;

/**
 * A wrapper for {@link ServletConfig} that provides convenience methods to retrieve
 * configuration parameters
 */
public final class ServletConfigWrapper {
    private final ServletConfig config;

    public ServletConfigWrapper(ServletConfig config) {
        this.config = config;
    }

    /**
     * @param paramName name of the initialization parameter
     * @return value of the parameter
     */
    public Optional<String> initParam(String paramName) {
        return Optional.ofNullable(config.getInitParameter(paramName));
    }

    /**
     * @param paramName name of the initialization parameter
     * @return long value of the parameter
     */
    public Optional<Long> initParamLong(String paramName) {
        return initParam(paramName).map(Long::valueOf);
    }
}

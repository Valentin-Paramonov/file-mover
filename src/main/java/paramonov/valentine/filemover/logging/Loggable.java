package paramonov.valentine.filemover.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides methods to simplify logging
 */
public interface Loggable {
    /**
     * log a formated message with {@link Level#INFO}
     *
     * @param message string to be formatted
     * @param params  params to use for formatting
     */
    default void log(String message, Object... params) {
        getLogger().info(String.format(message, params));
    }

    /**
     * log an exception with {@link Level#SEVERE}
     *
     * @param exception
     */
    default void logException(Exception exception) {
        getLogger().log(Level.SEVERE, exception.getMessage(), exception);
    }

    /**
     * @return the associated logger
     */
    default Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }
}

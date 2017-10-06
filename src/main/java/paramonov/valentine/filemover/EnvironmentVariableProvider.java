package paramonov.valentine.filemover;

import java.util.Optional;

/**
 * Utility class to query environment variables
 */
class EnvironmentVariableProvider {
    /**
     * @param var          name of the environment variable (case-sensitive)
     * @param defaultValue the default value to use the variable doesn't exist
     * @return the value of the variable, converted to int
     */
    static int getIntEnv(String var, int defaultValue) {
        return Optional
            .ofNullable(System.getenv(var))
            .map(Integer::valueOf)
            .orElse(defaultValue);
    }
}

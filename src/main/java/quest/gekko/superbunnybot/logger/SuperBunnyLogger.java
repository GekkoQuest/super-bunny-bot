package quest.gekko.superbunnybot.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperBunnyLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuperBunnyLogger.class);

    public static void info(final String message) {
        LOGGER.info(message);
    }

    public static void warn(final String message) {
        LOGGER.warn(message);
    }

    public static void error(final String message, final Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}

package quest.gekko.superbunnybot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import quest.gekko.superbunnybot.logger.SuperBunnyLogger;

import java.io.InputStream;

public class ConfigurationLoader {

    public Configuration loadConfiguration() throws ConfigurationLoadException {
        try {
            final ClassLoader loader = Thread.currentThread().getContextClassLoader();
            final InputStream inputStream = loader.getResourceAsStream("config.yaml");

            final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(inputStream, Configuration.class);
        } catch (final Exception ex) {
            SuperBunnyLogger.error("Failed to load configuration!", ex);
            throw new ConfigurationLoadException("Failed to load configuration...", ex);
        }
    }

}

package quest.gekko.superbunnybot.configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class Configuration {
    private Map<String, String> bot;
    private Map<String, String> api;
    private Map<String, String> credentials;

    private List<String> channels;

    @Override
    public String toString() {
        return "Configuration{" +
                "bot=" + bot +
                ", api=" + api +
                ", credentials=" + credentials +
                ", channels=" + channels +
                '}';
    }
}

package quest.gekko.superbunnybot.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitch.bot")
@Getter
@Setter
public class TwitchProperties {
    private String clientId;
    private String clientSecret;
    private String authToken;

    private String[] channels;
}

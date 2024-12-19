package quest.gekko.superbunnybot.configuration;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TwitchConfiguration {
    private final TwitchProperties twitchProperties;

    @Bean
    public TwitchClient twitchClient() {
        final String clientId = twitchProperties.getClientId();
        final String clientSecret = twitchProperties.getClientSecret();
        final String authToken = twitchProperties.getAuthToken();

        final OAuth2Credential credential = new OAuth2Credential("twitch", authToken);

        final TwitchClient client = TwitchClientBuilder.builder()
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withEnableHelix(true)
                .withChatAccount(credential)
                .withEnableChat(true)
                .build();

        final String[] channels = twitchProperties.getChannels();

        for (final String channel : channels) {
            client.getChat().joinChannel(channel);
        }

        return client;
    }
}

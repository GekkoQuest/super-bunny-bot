package quest.gekko.superbunnybot.service.twitch;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import lombok.Getter;
import quest.gekko.superbunnybot.configuration.Configuration;
import quest.gekko.superbunnybot.provider.CredentialsProvider;

@Getter
public class TwitchService {
    private final ITwitchClient twitchClient;

    public TwitchService(final Configuration configuration, final CredentialsProvider credentialsProvider) {
        final OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                credentialsProvider.getAuthToken()
        );

        this.twitchClient = TwitchClientBuilder.builder()
                .withClientId(credentialsProvider.getClientId())
                .withClientSecret(credentialsProvider.getClientSecret())
                .withEnableHelix(true)
                .withChatAccount(credential)
                .withEnableChat(true)
                .build();
    }
}

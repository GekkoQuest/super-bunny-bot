package quest.gekko.superbunnybot.provider;

import lombok.RequiredArgsConstructor;
import quest.gekko.superbunnybot.configuration.Configuration;

@RequiredArgsConstructor
public class CredentialsProvider {
    private final Configuration configuration;

    public String getAuthToken() {
        return configuration.getCredentials().get("irc");
    }

    public String getClientId() {
        return configuration.getApi().get("twitch_client_id");
    }

    public String getClientSecret() {
        return configuration.getApi().get("twitch_client_secret");
    }
}

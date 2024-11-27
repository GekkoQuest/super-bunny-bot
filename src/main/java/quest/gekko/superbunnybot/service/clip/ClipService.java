package quest.gekko.superbunnybot.service.clip;

import com.github.twitch4j.ITwitchClient;
import quest.gekko.superbunnybot.logger.SuperBunnyLogger;
import quest.gekko.superbunnybot.provider.CredentialsProvider;

public class ClipService {
    private final ITwitchClient twitchClient;
    private final CredentialsProvider credentialsProvider;

    public ClipService(final ITwitchClient twitchClient, final CredentialsProvider credentialsProvider) {
        this.twitchClient = twitchClient;
        this.credentialsProvider = credentialsProvider;
    }

    public String createClip(final String channelName) {
        final String authToken = credentialsProvider.getAuthToken();

        try {
            return twitchClient.getHelix()
                    .createClip(authToken, channelName, false)
                    .execute()
                    .getData().getFirst()
                    .getEditUrl();
        } catch (final Exception ex) {
            SuperBunnyLogger.error("Failed to create clip!", ex);
            return null;
        }
    }

}

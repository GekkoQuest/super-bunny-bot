package quest.gekko.superbunnybot.service.clip;

import com.github.twitch4j.TwitchClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quest.gekko.superbunnybot.configuration.TwitchProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClipService {
    private final TwitchClient twitchClient;
    private final TwitchProperties twitchProperties;

    public String createClip(final String channelId) {
        final String authToken = twitchProperties.getAuthToken();

        try {
            return twitchClient.getHelix()
                            .createClip(authToken, channelId, false)
                            .execute()
                            .getData()
                            .getFirst()
                            .getEditUrl();
        } catch (final Exception ex) {
            log.error("Failed to create clip", ex);
            return "Unable to generate clip.";
        }
    }
}

package quest.gekko.superbunnybot.service.clip;

import com.github.twitch4j.ITwitchClient;
import lombok.RequiredArgsConstructor;
import quest.gekko.superbunnybot.SuperBunnyBot;

@RequiredArgsConstructor
public class ClipService {
    private final SuperBunnyBot superBunnyBot;

    public String createClip(final String channelName) {
        final ITwitchClient twitchClient = superBunnyBot.getTwitchClient();
        final String authToken = superBunnyBot.getConfiguration().getCredentials().get("irc").substring(6);

        try {
            return twitchClient.getHelix()
                    .createClip(authToken, channelName, false)
                    .execute()
                    .getData().getFirst()
                    .getEditUrl();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}

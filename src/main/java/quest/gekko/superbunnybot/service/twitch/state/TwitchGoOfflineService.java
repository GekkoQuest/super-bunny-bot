package quest.gekko.superbunnybot.service.twitch.state;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quest.gekko.superbunnybot.service.channel.ChannelDataService;

@Service
@Slf4j
public class TwitchGoOfflineService {
    private final ChannelDataService channelDataService;

    public TwitchGoOfflineService(final TwitchClient twitchClient) {
        this.channelDataService = new ChannelDataService();

        twitchClient.getEventManager().onEvent(ChannelGoOfflineEvent.class, this::onChannelGoOffline);
    }

    public void onChannelGoOffline(final ChannelGoOfflineEvent event) {
        final String channelName = event.getChannel().getName();
        channelDataService.getChannelData(channelName).setLive(false);
    }
}

package quest.gekko.superbunnybot.service.twitch.state;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quest.gekko.superbunnybot.channel.ChannelData;
import quest.gekko.superbunnybot.service.channel.ChannelDataService;

@Service
@Slf4j
public class TwitchGoLiveService {
    final ChannelDataService channelDataService;

    public TwitchGoLiveService(final TwitchClient twitchClient) {
        this.channelDataService = new ChannelDataService();

        twitchClient.getEventManager().onEvent(ChannelGoLiveEvent.class, this::onChannelGoLive);
    }

    public void onChannelGoLive(final ChannelGoLiveEvent event) {
        final String channelName = event.getChannel().getName();
        final String channelId = event.getChannel().getId();

        ChannelData channelData = channelDataService.getChannelData(channelName);

        if (channelData == null) {
            channelData = new ChannelData(channelName, channelId);
            channelDataService.addChannelData(channelName, channelId);
        }

        channelData.setLive(true);
    }
}

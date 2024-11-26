package quest.gekko.superbunnybot.listener;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import quest.gekko.superbunnybot.SuperBunnyBot;

public class ChannelGoOfflineListener {
    private final SuperBunnyBot superBunnyBot;

    public ChannelGoOfflineListener(final SimpleEventHandler eventHandler, final SuperBunnyBot superBunnyBot) {
        eventHandler.onEvent(ChannelGoOfflineEvent.class, this::onChannelGoOffline);
        this.superBunnyBot = superBunnyBot;
    }

    public void onChannelGoOffline(final ChannelGoOfflineEvent event) {
        final String channelName = event.getChannel().getName();
        superBunnyBot.getChannelDataService().getChannelData(channelName).setLive(false);
    }

}

package quest.gekko.superbunnybot.listener.state;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import quest.gekko.superbunnybot.SuperBunnyBot;

public class ChannelGoOfflineListener {

    public ChannelGoOfflineListener(final SimpleEventHandler eventHandler) {
        eventHandler.onEvent(ChannelGoOfflineEvent.class, this::onChannelGoOffline);
    }

    public void onChannelGoOffline(final ChannelGoOfflineEvent event) {
        // TODO: Webhook notification saying channel is offline(?)
    }

}

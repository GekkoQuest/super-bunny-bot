package quest.gekko.superbunnybot.listener.chat;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import quest.gekko.superbunnybot.SuperBunnyBot;

public class ChannelMessageListener {
    private final SuperBunnyBot superBunnyBot;

    public ChannelMessageListener(final SimpleEventHandler eventHandler, final SuperBunnyBot superBunnyBot) {
        eventHandler.onEvent(ChannelMessageEvent.class, this::onChannelMessage);
        this.superBunnyBot = superBunnyBot;
    }

    public void onChannelMessage(final ChannelMessageEvent event) {
        superBunnyBot.getCommandManager().handleCommand(event);
    }
}

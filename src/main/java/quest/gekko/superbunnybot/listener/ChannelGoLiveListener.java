package quest.gekko.superbunnybot.listener;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import quest.gekko.superbunnybot.SuperBunnyBot;
import quest.gekko.superbunnybot.service.discord.DiscordWebhookService;

public class ChannelGoLiveListener {
    private final SuperBunnyBot superBunnyBot;
    private final DiscordWebhookService webhookService = new DiscordWebhookService();

    public ChannelGoLiveListener(final SimpleEventHandler eventHandler, final SuperBunnyBot superBunnyBot) {
        eventHandler.onEvent(ChannelGoLiveEvent.class, this::onChannelGoLive);
        this.superBunnyBot = superBunnyBot;
    }

    public void onChannelGoLive(final ChannelGoLiveEvent event) {
        final String channelName = event.getChannel().getName();
        final String gameName = event.getStream().getGameName();

        final String liveMessage = "%s is now live and playing %s".formatted(channelName, gameName);
        webhookService.sendNotification(liveMessage);

        superBunnyBot.getChannelDataService().getChannelData(channelName).setLive(true);
    }

}

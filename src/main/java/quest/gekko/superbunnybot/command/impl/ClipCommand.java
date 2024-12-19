package quest.gekko.superbunnybot.command.impl;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.springframework.stereotype.Component;
import quest.gekko.superbunnybot.command.base.Command;
import quest.gekko.superbunnybot.command.base.CommandQualifier;
import quest.gekko.superbunnybot.service.channel.ChannelDataService;
import quest.gekko.superbunnybot.service.clip.ClipService;

@Component
@CommandQualifier
public class ClipCommand implements Command {
    private final ClipService clipService;
    private final ChannelDataService channelDataService;

    public ClipCommand(final ClipService clipService) {
        this.clipService = clipService;
        this.channelDataService = new ChannelDataService();
    }

    @Override
    public String getName() {
        return "clip";
    }

    @Override
    public String getDescription() {
        return "Clips the latest 15 seconds of the channel's stream.";
    }

    @Override
    public void execute(ChannelMessageEvent event, String[] args) {
        final String channelName = event.getChannel().getName();
        final String channelId = event.getChannel().getId();

        final boolean isLive = channelDataService.getChannelData(channelId).isLive();

        if (!isLive) {
            event.getTwitchChat().sendMessage(channelName, "This channel is currently offline. No clip can be created.");
            return;
        }

        final String clipUrl = clipService.createClip(channelId);
        event.getTwitchChat().sendMessage(channelName, "Clip created: %s".formatted(clipUrl));
    }
}

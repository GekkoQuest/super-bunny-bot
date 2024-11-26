package quest.gekko.superbunnybot.command.impl.general;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.RequiredArgsConstructor;
import quest.gekko.superbunnybot.SuperBunnyBot;
import quest.gekko.superbunnybot.command.base.Command;

@RequiredArgsConstructor
public class ClipCommand implements Command {
    private final SuperBunnyBot superBunnyBot;

    @Override
    public String getName() {
        return "clip";
    }

    @Override
    public String getDescription() {
        return "Clips the latest 15 seconds of stream.";
    }

    @Override
    public void execute(ChannelMessageEvent event, String[] args) {
        final String channelName = event.getChannel().getName();

        final boolean isChannelLive = superBunnyBot.getChannelDataService().getChannelData(channelName).isLive();
        if (!isChannelLive) {
            event.getTwitchChat().sendMessage(channelName, "Cannot clip a channel that is currently offline!");
            return;
        }

        final String clipLink = superBunnyBot.getClipService().createClip("");
        event.getTwitchChat().sendMessage(channelName, clipLink);
    }
}

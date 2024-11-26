package quest.gekko.superbunnybot.command.base;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public interface Command {
    String getName();
    String getDescription();

    void execute(final ChannelMessageEvent event, final String[] args);
}

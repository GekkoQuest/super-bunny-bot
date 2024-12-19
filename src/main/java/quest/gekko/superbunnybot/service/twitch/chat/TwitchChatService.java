package quest.gekko.superbunnybot.service.twitch.chat;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quest.gekko.superbunnybot.command.base.CommandManager;

@Service
@Slf4j
public class TwitchChatService {
    private final CommandManager commandManager;

    public TwitchChatService(TwitchClient twitchClient, final CommandManager commandManager) {
        this.commandManager = commandManager;

        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, this::onChannelMessage);
    }

    public void onChannelMessage(ChannelMessageEvent event) {
        commandManager.handleCommand(event);
    }
}

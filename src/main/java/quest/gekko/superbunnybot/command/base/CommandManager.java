package quest.gekko.superbunnybot.command.base;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CommandManager {
    private final Map<String, Command> commandMap = new HashMap<>();

    public void registerCommand(final Command command) {
        commandMap.put(command.getName().toLowerCase(), command);
    }

    public void handleCommand(final ChannelMessageEvent event) {
        final String message = event.getMessage();

        if (!message.startsWith("!"))
            return;

        final String[] parts = message.substring(1).split("\\s+");
        final String commandName = parts[0].toLowerCase();
        final String[] args = parts.length > 1 ? message.substring(commandName.length() + 2).split("\\s+") : new String[0];

        final Command command = commandMap.get(commandName);

        System.out.println(commandName + " : " + command.toString());

        if (command == null)
            return;

        command.execute(event, args);
    }
}

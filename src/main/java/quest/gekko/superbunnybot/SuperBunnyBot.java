package quest.gekko.superbunnybot;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import lombok.Getter;
import quest.gekko.superbunnybot.command.base.CommandManager;
import quest.gekko.superbunnybot.command.impl.fun.EightBallCommand;
import quest.gekko.superbunnybot.command.impl.general.ClipCommand;
import quest.gekko.superbunnybot.configuration.Configuration;
import quest.gekko.superbunnybot.configuration.ConfigurationLoader;
import quest.gekko.superbunnybot.listener.state.ChannelGoLiveListener;
import quest.gekko.superbunnybot.listener.state.ChannelGoOfflineListener;
import quest.gekko.superbunnybot.listener.chat.ChannelMessageListener;
import quest.gekko.superbunnybot.provider.CredentialsProvider;
import quest.gekko.superbunnybot.service.clip.ClipService;
import quest.gekko.superbunnybot.service.twitch.TwitchService;

@Getter
public class SuperBunnyBot {
    private final Configuration configuration;
    private final CredentialsProvider credentialsProvider;

    private TwitchService twitchService;
    private ClipService clipService;
    private CommandManager commandManager;

    public SuperBunnyBot() {
        final ConfigurationLoader configurationLoader = new ConfigurationLoader();
        configuration = configurationLoader.loadConfiguration();

        credentialsProvider = new CredentialsProvider(configuration);
    }

    public void registerListeners() {
        final SimpleEventHandler eventHandler = twitchService.getTwitchClient().getEventManager().getEventHandler(SimpleEventHandler.class);

        new ChannelGoLiveListener(eventHandler);
        new ChannelGoOfflineListener(eventHandler);
        new ChannelMessageListener(eventHandler, this);
    }

    public void registerServices() {
        twitchService = new TwitchService(configuration, credentialsProvider);
        clipService = new ClipService(twitchService.getTwitchClient(), credentialsProvider);
    }

    public void registerCommands() {
        commandManager = new CommandManager();
        commandManager.registerCommand(new ClipCommand(this));
        commandManager.registerCommand(new EightBallCommand());
    }

    public void start() {
        final ITwitchClient twitchClient = twitchService.getTwitchClient();

        configuration.getChannels().forEach(channel -> {
            twitchClient.getChat().joinChannel(channel);

            twitchClient.getClientHelper().enableStreamEventListener(channel);
            twitchClient.getClientHelper().enableFollowEventListener(channel);
            twitchClient.getClientHelper().enableClipEventListener(channel);
        });
    }
}

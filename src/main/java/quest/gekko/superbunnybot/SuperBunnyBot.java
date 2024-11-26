package quest.gekko.superbunnybot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import lombok.Getter;
import quest.gekko.superbunnybot.command.base.CommandManager;
import quest.gekko.superbunnybot.command.impl.fun.EightBallCommand;
import quest.gekko.superbunnybot.command.impl.general.ClipCommand;
import quest.gekko.superbunnybot.configuration.Configuration;
import quest.gekko.superbunnybot.listener.ChannelGoLiveListener;
import quest.gekko.superbunnybot.listener.ChannelGoOfflineListener;
import quest.gekko.superbunnybot.listener.ChannelMessageListener;
import quest.gekko.superbunnybot.service.channel.ChannelDataService;
import quest.gekko.superbunnybot.service.clip.ClipService;

import java.io.InputStream;

@Getter
public class SuperBunnyBot {
    private final ITwitchClient twitchClient;

    private Configuration configuration;

    private ChannelDataService channelDataService;
    private ClipService clipService;

    private CommandManager commandManager;

    public SuperBunnyBot() {
        loadConfiguration();

        final String authToken = configuration.getCredentials().get("irc");

        final OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                authToken
        );

        final String clientId     = configuration.getApi().get("twitch_client_id");
        final String clientSecret = configuration.getApi().get("twitch_client_secret");

        twitchClient = TwitchClientBuilder.builder().
                withClientId(clientId)
                .withClientSecret(clientSecret)
                .withEnableHelix(true)
                .withChatAccount(credential)
                .withEnableChat(true)
                .build();
    }

    private void loadConfiguration() {
        try {
            final ClassLoader loader = Thread.currentThread().getContextClassLoader();
            final InputStream inputStream = loader.getResourceAsStream("config.yaml");

            final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configuration = mapper.readValue(inputStream, Configuration.class);
        } catch (final Exception ex) {
            ex.printStackTrace();
            System.out.println("Failed to load configuration...");
            System.exit(1);
        }
    }

    public void registerListeners() {
        final SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);

        final ChannelGoLiveListener channelGoLiveListener = new ChannelGoLiveListener(eventHandler, this);
        final ChannelGoOfflineListener channelGoOfflineListener = new ChannelGoOfflineListener(eventHandler, this);
        final ChannelMessageListener channelMessageListener = new ChannelMessageListener(eventHandler, this);
    }

    public void registerServices() {
        channelDataService = new ChannelDataService();
        clipService = new ClipService(this);
    }

    public void registerCommands() {
        commandManager = new CommandManager();
        commandManager.registerCommand(new ClipCommand(this));
        commandManager.registerCommand(new EightBallCommand());
    }

    public void start() {
        configuration.getChannels().forEach(channel -> {
            twitchClient.getChat().joinChannel(channel);

            channelDataService.addChannel(channel.toLowerCase());

            twitchClient.getClientHelper().enableStreamEventListener(channel);
            twitchClient.getClientHelper().enableFollowEventListener(channel);
            twitchClient.getClientHelper().enableClipEventListener(channel);
        });
    }
}

package quest.gekko.superbunnybot.command.impl.fun;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.json.JSONObject;
import quest.gekko.superbunnybot.command.base.Command;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EightBallCommand implements Command {
    @Override
    public String getName() {
        return "8ball";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void execute(ChannelMessageEvent event, String[] args) {
        final String channelName = event.getChannel().getName();
        final String question = String.join(" ", args).trim();

        if (question.isEmpty()) {
            event.getTwitchChat().sendMessage(channelName, "You need to provide a question!");
            return;
        }

        try {
            final HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://eightballapi.com/api"))
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            final JSONObject jsonResponse = new JSONObject(response.body());
            final String reading = jsonResponse.getString("reading");

            event.getTwitchChat().sendMessage(channelName, reading);
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}

package quest.gekko.superbunnybot.service.discord;

import lombok.RequiredArgsConstructor;
import quest.gekko.superbunnybot.logger.SuperBunnyLogger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class DiscordWebhookService {
    // Temporary. Will make this modifiable for each Twitch channel down the line.
    private final String webhookUrl = "https://discord.com/api/webhooks/1310998893718405220/QNl0wS-aZ0XJSYqr9qy2v06XCGW-Ib81dXDiI0Qhe8oGvSzih2ThU4-yM1aKGzx-t5il";

    public void sendNotification(final String message) {
        try {
            final HttpClient client = HttpClient.newHttpClient();
            final String jsonPayload = "{\"content\": \"" + message + "\"}";

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webhookUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .header("Content-Type", "application/json")
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (final Exception ex) {
            SuperBunnyLogger.error("Failed to send notification!", ex);
        }
    }
}

package quest.gekko.superbunnybot.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ChannelData {
    private final String channelName;
    private final String channelId;

    @Setter
    private boolean isLive;
}
package quest.gekko.superbunnybot.service.channel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class ChannelData {
    @Setter(AccessLevel.NONE)
    private final String channelName;

    private boolean isLive;
}

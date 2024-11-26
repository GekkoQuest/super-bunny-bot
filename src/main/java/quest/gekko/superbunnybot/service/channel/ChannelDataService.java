package quest.gekko.superbunnybot.service.channel;

import java.util.HashMap;
import java.util.Map;

public class ChannelDataService {
    private final Map<String, ChannelData> channelDataMap = new HashMap<>();

    public void addChannel(final String channelName) {
        channelDataMap.put(channelName, new ChannelData(channelName));
    }

    public void removeChannel(final String channelName) {
        channelDataMap.remove(channelName);
    }

    public ChannelData getChannelData(final String channelName) {
        return channelDataMap.get(channelName);
    }
}

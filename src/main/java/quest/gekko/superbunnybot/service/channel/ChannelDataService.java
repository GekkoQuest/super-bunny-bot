package quest.gekko.superbunnybot.service.channel;

import org.springframework.stereotype.Service;
import quest.gekko.superbunnybot.channel.ChannelData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChannelDataService {
    private final Map<String, ChannelData> channelDataMap = new ConcurrentHashMap<>();

    public void addChannelData(final String channelName, final String channelId) {
        channelDataMap.put(channelName, new ChannelData(channelId, channelName));
    }

    public void removeChannelData(final String channelName) {
        channelDataMap.remove(channelName);
    }

    public ChannelData getChannelData(final String channelName) {
        return channelDataMap.get(channelName);
    }
}

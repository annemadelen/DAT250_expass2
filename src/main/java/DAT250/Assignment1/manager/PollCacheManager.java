package DAT250.Assignment1.manager;

import java.util.Map;
import redis.clients.jedis.UnifiedJedis;

public class PollCacheManager {
    private final UnifiedJedis jedis;

    public PollCacheManager() {
        this.jedis = new UnifiedJedis("redis://localhost:6379");
    }

    public Map<String, String> getPoll(String pollId) {
        String key = "poll:" + pollId;
        Map<String, String> pollData = jedis.hgetAll(key);

        if (pollData == null || pollData.isEmpty()) {
            System.out.println("Cache miss. Querying database...");
            pollData = Map.of(
                "options:1", "42",
                "options:2", "27",
                "options:3", "99"
            );

            jedis.hset(key, pollData);
            jedis.expire(key, 60); 
        } else {
            System.out.println("Cache hit!");
        }

        return pollData;
    }

    public void invalidatePoll(String pollId) {
        jedis.del("poll:" + pollId);
    }

    public void incrementVote(String pollId, String optionKey) {
        jedis.hincrBy("poll:" + pollId, optionKey, 1);
    }

    public void close() {
        jedis.close();
    }
}

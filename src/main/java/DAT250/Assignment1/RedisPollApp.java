package DAT250.Assignment1;

import java.util.Map;

import redis.clients.jedis.UnifiedJedis;

public class RedisPollApp {
    public static void main(String[] args) {
        try (UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379")) {
            String pollKey = "poll:03ebcb7b-bd69-440b-924e-f5b7d664af7b";
            jedis.del(pollKey);

            jedis.hset(pollKey, Map.of(
                "title", "Pineapple on Pizza?",
                "options:yes", "269",
                "options:no", "268",
                "options:meh", "42"
            ));

            jedis.hincrBy(pollKey, "options:yes", 1);

            System.out.println("Poll Data: " + jedis.hgetAll(pollKey));
        }
    }
}

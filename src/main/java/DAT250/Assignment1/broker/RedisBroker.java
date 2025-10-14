package DAT250.Assignment1.broker;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisBroker {
    private static final String REDIS_URL = "redis://localhost:6379";

    public static void publish(String topic, String message) {
        try (Jedis jedis = new Jedis(REDIS_URL)) {
            jedis.publish(topic, message);
            System.out.println("Published message to topic " + topic + ": " + message);
        }
    }

    public static void subscribe(String topic) {
        new Thread(() -> {
            try (Jedis jedis = new Jedis(REDIS_URL)) {
                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        System.out.println("📩 Received on " + channel + ": " + message);
                    }
                }, topic);
            }
        }).start();
        System.out.println("Subscribed to topic: " + topic);
    }
}

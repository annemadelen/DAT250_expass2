package DAT250.Assignment1.broker;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisBroker {

    private static final String REDIS_URL = "redis://localhost:6379";

    // Publish a message (for example, a vote)
    public static void publish(String topic, String message) {
        try (Jedis jedis = new Jedis(REDIS_URL)) {
            jedis.publish(topic, message);
            System.out.println("Published message to topic " + topic + ": " + message);
        }
    }

    // Subscribe to a topic (for example, to listen for votes)
    public static void subscribe(String topic) {
        new Thread(() -> {
            try (Jedis jedis = new Jedis(REDIS_URL)) {
                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        System.out.println("📩 Received on " + channel + ": " + message);
                        // You could later add logic to update poll data here
                    }
                }, topic);
            }
        }).start();
        System.out.println("Subscribed to topic: " + topic);
    }
}

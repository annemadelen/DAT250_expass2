package DAT250.Assignment1;

import DAT250.Assignment1.broker.RedisBroker;

public class RedisVoteListener {
    public static void main(String[] args) {
        System.out.println("Listening for votes on poll:1");
        RedisBroker.subscribe("poll:1");
    }
}

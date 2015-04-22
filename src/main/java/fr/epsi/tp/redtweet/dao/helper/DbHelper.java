package fr.epsi.tp.redtweet.dao.helper;

import redis.clients.jedis.Jedis;

/**
 * Created by Fx on 01/04/2015.
 */
public class DbHelper {

    public static final String redisHost = "127.0.0.1";
    private static final String auth = "";

    /**
     * Create a single connection
     *
     * @return
     */
    public static Jedis getJedis() {
        Jedis jedis = new Jedis(redisHost);
        //jedis.auth(auth);
        return jedis;
    }
}

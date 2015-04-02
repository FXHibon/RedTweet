package fr.epsi.tp.redtweet.dao.helper;

import redis.clients.jedis.Jedis;

/**
 * Created by Fx on 01/04/2015.
 */
public class DbHelper {

    public static final String redisHost = "127.0.0.1";
    private static final String auth = "|h!i1j_~!=+~~-M!-.R!.=kzOl.bQ+uuI.f_!.N.+Q;H=M;t.!F=H!|.~|:_X.-|X|;r|UR+^C._m5^~e*.%|^%y^=_!rs-po~=1j^!+zf*^+q%KCZ|A|~!Ftf|=;!=+hf=9%;K*uM=+.!~_|t%**G";

    /**
     * Create a single connection
     *
     * @return
     */
    public static Jedis getJedis() {
        Jedis jedis = new Jedis(redisHost);
//        jedis.auth(auth);
        return jedis;
    }
}

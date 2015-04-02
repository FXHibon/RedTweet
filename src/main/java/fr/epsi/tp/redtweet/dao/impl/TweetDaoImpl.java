package fr.epsi.tp.redtweet.dao.impl;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.TweetDao;
import fr.epsi.tp.redtweet.dao.helper.DbHelper;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Fx on 01/04/2015.
 */
public class TweetDaoImpl implements TweetDao {

    public List<String> getUserTweetsId(User owner) {
        Jedis jedis = DbHelper.getJedis();

        List<String> ids = jedis.lrange("user:" + owner.getUsername(), 0, -1);

        jedis.close();
        return ids;
    }

    public Tweet getTweet(String id) {
        Jedis jedis = DbHelper.getJedis();

        Map<String, String> tweetMap = jedis.hgetAll("tweet:" + id);

        jedis.close();
        return new Tweet(tweetMap);
    }

    public Set<String> getTimeLine(User ref, int start, int count) {
        Jedis jedis = DbHelper.getJedis();

        Set<String> tweetIds = jedis.zrange("user:" + ref.getUsername() + ":timeline", start, count);
        return tweetIds;
    }

    public void create(String author, Tweet tweet) {

    }
}

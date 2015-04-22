package fr.epsi.tp.redtweet.dao.impl;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.TweetDao;
import fr.epsi.tp.redtweet.dao.helper.DbHelper;
import org.joda.time.DateTime;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Fx on 01/04/2015.
 */
public class TweetDaoImpl implements TweetDao {

    public List<Tweet> getUserTimeLine(User ref) {
        Jedis jedis = DbHelper.getJedis();

        Set<String> ids = jedis.zrange("timeLine:" + ref.getUsername(), 0, -1);

        List<Tweet> tweets = new ArrayList<Tweet>();

        for (String id : ids) {
            tweets.add(new Tweet(jedis.hgetAll("tweet:" + id)));
        }
        jedis.close();
        return tweets;
    }

    public List<Tweet> getRetweets(String userName) {
        Jedis jedis = DbHelper.getJedis();

        Set<String> ids = jedis.smembers("user:" + userName + ":retweets");

        List<Tweet> tweets = new ArrayList<Tweet>();

        for (String id : ids) {
            tweets.add(new Tweet(jedis.hgetAll("tweet:" + id)));
        }
        jedis.close();
        return tweets;
    }

    public boolean retweet(User user, String id) {

        try {
            Jedis jedis = DbHelper.getJedis();
            jedis.sadd("user:" + user.getUsername() + ":tweets", id);
            jedis.sadd("user:" + user.getUsername() + ":retweets", id);
            jedis.zadd("timeLine:" + user.getUsername(), new DateTime().getMillis(), id);
            jedis.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean update(Tweet tweet) {
        try {
            Jedis jedis = DbHelper.getJedis();

            jedis.hmset("tweet:" + tweet.getId(), tweet);

            jedis.close();
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean destroy(String userName, String tweetId) {

        try {
            Jedis jedis = DbHelper.getJedis();

            if (jedis.hgetAll("tweet:" + tweetId).get("author").equals(userName)) {
                jedis.del("tweet:" + tweetId);
                jedis.srem("user:" + userName + ":tweets", tweetId);
                jedis.zrem("timeLine:" + userName, tweetId);
            }

            jedis.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Tweet tweet(User user, Tweet tweet) {

        try {
            Jedis jedis = DbHelper.getJedis();

            DateTime now = new DateTime();
            String uuid = UUID.randomUUID().toString();
            tweet.setId(uuid);
            tweet.setAuthor(user.getUsername());
            tweet.setCreatedAt(now.toString());
            jedis.hmset("tweet:" + tweet.getId(), tweet);
            jedis.zadd("timeLine:" + user.getUsername(), now.toDate().getTime(), uuid);
            jedis.sadd("user:" + user.getUsername() + ":tweets", uuid);

            Set<String> smembers = jedis.smembers("user:" + user.getUsername() + ":followers");

            for (String follower : smembers) {
                jedis.zadd("timeLine:" + follower, now.toDate().getTime(), uuid);
            }

            jedis.close();
        } catch (Exception e) {
            return null;
        }
        return tweet;
    }

    public void follow(String callerId, String targetId) {
        try {
            Jedis jedis = DbHelper.getJedis();
            jedis.sadd("user:" + targetId + ":followers", callerId);
            jedis.sadd("user:" + callerId + ":following", targetId);

            jedis.zunionstore("timeLine:" + callerId, "timeLine:" + callerId, "user:" + targetId + ":tweets");

            jedis.close();
        } catch (Exception e) {
        }
    }
}

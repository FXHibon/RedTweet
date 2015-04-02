package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.TweetDao;
import fr.epsi.tp.redtweet.service.RedService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fhibon on 01/04/2015.
 */
public class RedServiceImpl implements RedService {

    @Resource
    private TweetDao tweetDao;

    public List<Tweet> getUserTimeLine(User ref) {
        return tweetDao.getUserTimeLine(ref);
    }

    public List<Tweet> getRetweets(String userName) {
        return tweetDao.getRetweets(userName);
    }

    public Map<String, Object> retweet(String id) {

        Map<String, Object> map = new HashMap<String, Object>();

        String msg;
        if (tweetDao.retweet(id)) {
            msg = "OK";
        } else {
            msg = "KO";
        }
        map.put("msg", msg);

        return map;
    }

    public List<Tweet> getHomeTimeLine(User user) {
        return tweetDao.getHomeTimeLine(user);
    }

    public List<Tweet> getRetweetsOfMe(User user) {
        return tweetDao.getRetweetsOfMe(user);
    }

    public Map<String, Object> destroy(User user, String tweetId) {

        Map<String, Object> map = new HashMap<String, Object>();

        // TODO Check rights
        String msg;
        if (tweetDao.destroy(tweetId)) {
            msg = "OK";
        } else {
            msg = "KO";
        }

        map.put("msg", msg);

        return map;
    }

    public Map<String, Object> update(Tweet tweet, User user) {

        Map<String, Object> map = new HashMap<String, Object>();

        String msg;
        if (tweetDao.update(tweet)) {
            msg = "OK";
        } else {
            msg = "KO";
        }

        map.put("msg", msg);

        return map;
    }

    public Map<String, Object> tweet(User user, Tweet tweet) {

        Map<String, Object> map = new HashMap<String, Object>();

        String msg;
        if (tweetDao.tweet(user, tweet)) {
            msg = "OK";
        } else {
            msg = "KO";
        }

        map.put("msg", msg);

        return map;
    }
}

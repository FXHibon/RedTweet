package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.TweetDao;
import fr.epsi.tp.redtweet.dao.UserDao;
import fr.epsi.tp.redtweet.exception.UserNotFound;
import fr.epsi.tp.redtweet.service.RedService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fhibon on 01/04/2015.
 */
public class RedServiceImpl implements RedService {

    private final Logger logger = Logger.getLogger(RedServiceImpl.class);
    @Resource
    private TweetDao tweetDao;

    @Resource
    private UserDao userDao;

    public List<Tweet> getUserTimeLine(User ref) {
        return tweetDao.getUserTimeLine(ref);
    }

    public Map<String, Object> retweet(User user, String id) {

        Map<String, Object> map = new HashMap<String, Object>();

        String msg;
        if (tweetDao.retweet(user, id)) {
            msg = "OK";
        } else {
            msg = "KO";
        }
        map.put("msg", msg);

        return map;
    }

    public List<Tweet> getHomeTimeLine(User user) {
        return tweetDao.getUserTimeLine(user);
    }

    public List<Tweet> getRetweets(String userName) {
        return tweetDao.getRetweets(userName);
    }

    public List<Tweet> getRetweetsOfMe(User user) {
        return this.getRetweets(user.getUsername());
    }

    public Map<String, Object> destroy(User user, String tweetId) {

        Map<String, Object> map = new HashMap<String, Object>();

        // TODO Check rights
        String msg;
        if (tweetDao.destroy(user.getUsername(), tweetId)) {
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

    public Map<String, String> tweet(User user, Tweet tweet) {

        Map<String, String> map = new HashMap<String, String>();

        String msg;
        Tweet creation = tweetDao.tweet(user, tweet);
        if (creation == null) {
            msg = "KO";
            map.put("msg", msg);
            return map;
        } else {
            return creation;
        }
    }

    public Map search(String query) {
        Map map = new HashMap();

        try {
            User read = userDao.read(query);
            map.put("user", read);
        } catch (UserNotFound userNotFound) {
            logger.error(userNotFound);

        }

        return map;
    }

    public void follow(User caller, User target) {
        if (!caller.getUsername().equals(target.getUsername())) {
            tweetDao.follow(caller.getUsername(), target.getUsername());
        }
    }
}

package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.TweetDao;
import fr.epsi.tp.redtweet.service.TweetService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
public class TweetServiceImpl implements TweetService {

    @Resource
    private TweetDao tweetDao;

    public List<Tweet> getUserTimeLine(User ref) {
        List<Tweet> res;
        res = new ArrayList<Tweet>();
        return res;
    }

    public List<Tweet> getUserTweets(User owner) {
        List<String> ids = tweetDao.getUserTweetsId(owner);

        List<Tweet> results = new ArrayList<Tweet>();
        for (String id : ids) {
            results.add(tweetDao.getTweet(id));
        }

        return results;
    }

    public void postTweet(Tweet tweet) {

    }


}

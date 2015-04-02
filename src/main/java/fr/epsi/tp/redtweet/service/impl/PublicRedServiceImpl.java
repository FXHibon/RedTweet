package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.TweetDao;
import fr.epsi.tp.redtweet.interfaces.PublicRedService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by fhibon on 01/04/2015.
 */
public class PublicRedServiceImpl implements PublicRedService {

    @Resource
    private TweetDao tweetDao;

    public List<Tweet> getUserTimeLine(User ref, int start, int count) {
        List<Tweet> res = new ArrayList<Tweet>();

        Set<String> tweetIds = tweetDao.getTimeLine(ref, start, count);

        for (String tweetId : tweetIds) {
            res.add(tweetDao.getTweet(tweetId));
        }

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

    public void postTweet(User author, Tweet tweet) {
        tweetDao.create(author.getUsername(), tweet);
    }


}

package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;

import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
public interface TweetDao {

    List<Tweet> getUserTimeLine(User ref);

    List<Tweet> getRetweets(String userName);

    boolean retweet(User user, String id);

    boolean update(Tweet tweet);

    boolean destroy(String userName, String tweetId);

    Tweet tweet(User user, Tweet tweet);

    void follow(String callerId, String targetId);
}

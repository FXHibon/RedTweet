package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;

import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
public interface TweetDao {

    /**
     * Find ref time line, from caller point of view
     *
     * @param ref
     * @param caller
     * @return
     */
    List<Tweet> getUserTimeLine(User ref, User caller);

    List<Tweet> getRetweets(String userName);

    boolean retweet(User user, String id);

    boolean update(Tweet tweet);

    boolean destroy(String userName, String tweetId);

    Tweet tweet(User user, Tweet tweet);

    void follow(String callerId, String targetId);

    void favorite(String username, String tweetId);

    void unfavorite(String callerUsername, String tweetId);
}

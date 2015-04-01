package fr.epsi.tp.redtweet.service;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;

import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
public interface TweetService {

    /**
     * Find tweets for "home screen" of ref User
     *
     * @param ref
     * @return
     */
    List<Tweet> getUserTimeLine(User ref);

    /**
     * Find user's tweet
     *
     * @param owner
     * @return
     */
    List<Tweet> getUserTweets(User owner);

    /**
     * Post a simple tweet
     *
     * @param tweet
     */
    void postTweet(Tweet tweet);
}

package fr.epsi.tp.redtweet.service;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;

import java.util.List;
import java.util.Map;

/**
 * Created by fhibon on 02/04/2015.
 */
public interface RedService {

    /**
     * Get user time line
     *
     * @param ref User to get the timeline
     * @return Timeline of the user
     */
    List<Tweet> getUserTimeLine(User ref);

    /**
     * Get retweets of given user
     *
     * @param userName User to get retweets
     * @return List of retweets
     */
    List<Tweet> getRetweets(String userName);


    /**
     * Retweet
     *
     *
     * @param id Id of tweet to be retweeted
     * @return Info message
     */
    Map<String, Object> retweet(User user, String id);

    /**
     * Get connected user timeline
     *
     * @return Timeline of the connected user
     */
    List<Tweet> getHomeTimeLine(User user);

    /**
     * Get connected user retweets
     *
     * @return List of retweet
     */
    List<Tweet> getRetweetsOfMe(User user);

    /**
     * Destroy a tweet
     *
     * @return Info message
     */
    Map<String, Object> destroy(User user, String tweetId);

    /**
     * Update the given tweet
     *
     * @param tweet Tweet to be updated
     * @return Info message
     */
    Map<String, Object> update(Tweet tweet, User user);

    /**
     * Post a single tweet
     *
     * @param ref Tweet's author
     * @param tweet Tweet to be created
     * @return Info message
     */
    Map<String, String> tweet(User ref, Tweet tweet);

    /**
     * Search for people, tags ...
     *
     * @param query Query to be used for search
     * @return Results
     */
    Map search(String query);
}

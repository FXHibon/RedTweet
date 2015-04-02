package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;

import java.util.List;
import java.util.Set;

/**
 * Created by fhibon on 01/04/2015.
 */
public interface TweetDao {
    List<String> getUserTweetsId(User owner);

    Tweet getTweet(String id);

    Set<String> getTimeLine(User ref, int start, int count);

    void create(String username, Tweet tweet);
}

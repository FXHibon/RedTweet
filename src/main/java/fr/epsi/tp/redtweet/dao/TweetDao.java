package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;

import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
public interface TweetDao {
    List<String> getUserTweetsId(User owner);

    Tweet getTweet(String id);
}

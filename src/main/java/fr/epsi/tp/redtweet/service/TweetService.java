package fr.epsi.tp.redtweet.service;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;

import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
public interface TweetService {

    List<Tweet> getAll(User user);
}

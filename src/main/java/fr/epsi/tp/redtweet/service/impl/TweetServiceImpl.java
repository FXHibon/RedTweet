package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.Tweet;
import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.TweetDao;
import fr.epsi.tp.redtweet.service.TweetService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fhibon on 01/04/2015.
 */
public class TweetServiceImpl implements TweetService {

    @Resource
    private TweetDao tweetDao;

    public List<Tweet> getAll(User owner) {
        tweetDao.findByOwner(owner);
        return null;
    }

    public boolean create(Tweet tweet) {

    }
}

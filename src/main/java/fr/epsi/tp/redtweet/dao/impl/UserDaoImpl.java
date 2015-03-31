package fr.epsi.tp.redtweet.dao.impl;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.UserDao;
import fr.epsi.tp.redtweet.exception.UserNotFound;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by fx on 26/03/2015.
 */
public class UserDaoImpl implements UserDao {

    private Jedis jedis = new Jedis("localhost");

    public boolean create(User user) {
        try {
            jedis.hmset("user:" + user.getUsername(), user.getMap());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User read(String userName) throws UserNotFound {
        Map<String, String> userMap = jedis.hgetAll("user:" + userName);

        if (userMap != null) {
            return new User(userMap);
        } else {
            throw new UserNotFound();
        }
    }

    public boolean update(User user) {
        try {
            jedis.hmset("user:" + user.getUsername(), user.getMap());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete(User user) {
        jedis.del("user:" + user.getUsername());
        return false;
    }
}

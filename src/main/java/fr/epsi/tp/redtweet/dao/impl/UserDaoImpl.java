package fr.epsi.tp.redtweet.dao.impl;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.UserDao;
import fr.epsi.tp.redtweet.dao.helper.DbHelper;
import fr.epsi.tp.redtweet.exception.UserNotFound;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by fx on 26/03/2015.
 */
public class UserDaoImpl implements UserDao {

    public boolean create(User user) {
        try {
            Jedis jedis = DbHelper.getJedis();
            jedis.hmset("user:" + user.getUsername(), user.getMap());
            jedis.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User read(String userName) throws UserNotFound {
        Jedis jedis = DbHelper.getJedis();
        Map<String, String> userMap = jedis.hgetAll("user:" + userName);
        jedis.close();
        if (userMap != null && userMap.keySet().size() > 0) {
            return new User(userMap);
        } else {
            throw new UserNotFound();
        }
    }

    public boolean update(User user) {
        try {
            Jedis jedis = DbHelper.getJedis();
            jedis.hmset("user:" + user.getUsername(), user.getMap());
            jedis.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete(User user) {
        try {
            Jedis jedis = DbHelper.getJedis();
            jedis.del("user:" + user.getUsername());
            jedis.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
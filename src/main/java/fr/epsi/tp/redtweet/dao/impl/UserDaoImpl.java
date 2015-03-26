package fr.epsi.tp.redtweet.dao.impl;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.UserDao;
import redis.clients.jedis.Jedis;

/**
 * Created by fx on 26/03/2015.
 */
public class UserDaoImpl implements UserDao {

    private Jedis jedis = new Jedis("localhost");

    public boolean create(User user) {
        return false;
    }

    public User read(String userName) {
//        jedis.hmget("user:" + userName, )

        return null;
    }

    public boolean update(User user) {
        return false;
    }

    public boolean delete(User user) {
        return false;
    }
}

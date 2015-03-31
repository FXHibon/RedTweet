package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.impl.UserDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by fhibon on 30/03/2015.
 */
public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();
    private Jedis jedis = new Jedis("localhost");

    private User user;

    @Before
    public void before() {
        user = new User();
        user
                .setFirstName("François-Xavier")
                .setLastName("HIBON")
                .setUsername("FXHibon")
                .setPassword("password");
    }

    @After
    public void after() {
        jedis.del("user:" + user.getUsername());
    }

    @Test
    public void testCreate() throws Exception {

        boolean exists = jedis.exists("user:" + user.getUsername());
        assertThat("should not exists", exists, is(false));

        boolean res = userDao.create(user);
        assertThat("creation should be ok", res, is(true));

        exists = jedis.exists("user:" + user.getUsername());
        assertThat("should exists", exists, is(true));

    }

    @Test
    public void testRead() throws Exception {
        userDao.create(user);

        User found = userDao.read(user.getUsername());
        assertThat("should be equal", user, equalTo(found));

    }

    @Test
    public void testUpdate() throws Exception {
        userDao.create(user);
        User found = userDao.read(user.getUsername());
        assertThat("should be equals", found, equalTo(user));

        found.setPassword("newOne");
        userDao.update(found);

        found = userDao.read(user.getUsername());
        assertThat("should not be equal", found, not(equalTo(user)));
    }

    @Test
    public void testDelete() throws Exception {
        userDao.create(user);

        assertThat("should exists", jedis.exists("user:" + user.getUsername()), is(true));
        userDao.delete(user);
        assertThat("should not exists anymore", jedis.exists("user:" + user.getUsername()), is(false));
    }
}
package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.helper.DbHelper;
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

    private UserDao userDao;
    private Jedis jedis;

    private User user;

    @Before
    public void before() {
        jedis = DbHelper.getJedis();
        userDao = new UserDaoImpl();

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
        jedis.close();
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
        boolean delete = userDao.delete(user);
        assertThat("deletion should be OK", delete, is(true));
        assertThat("should not exists anymore", jedis.exists("user:" + user.getUsername()), is(false));
    }
}
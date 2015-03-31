package fr.epsi.tp.redtweet.service;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by fhibon on 31/03/2015.
 */
public class UserServiceTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test.xml");

    private UserService userService = (UserService) applicationContext.getBean("userService");

    @Test
    public void testAuth() throws Exception {

        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.create(new User()
                        .setUsername("TEST")
                        .setPassword("password")
        );

        boolean auth;
        User user = new User();
        user.setUsername("wrong");
        user.setPassword("wrong");

        auth = userService.auth(user);
        assertThat("shoud not be connected", auth, is(false));

        user.setUsername("TEST");
        user.setPassword("wrong");

        auth = userService.auth(user);
        assertThat("shoud not be connected", auth, is(false));

        user.setUsername("TEST");
        user.setPassword("password");

        auth = userService.auth(user);
        assertThat("shoud be connected", auth, is(true));
    }
}
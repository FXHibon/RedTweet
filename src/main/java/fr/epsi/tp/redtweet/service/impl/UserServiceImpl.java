package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.UserDao;
import fr.epsi.tp.redtweet.exception.UserNotFound;
import fr.epsi.tp.redtweet.service.UserService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * Created by fx on 23/03/2015.
 */
public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    public boolean auth(User user) {
        logger.info("authenticating user:" + user);
        try {
            User userTmp = userDao.read(user.getUsername());
            return userTmp.getPassword().equals(userTmp.getPassword());
        } catch (UserNotFound e) {

        }
        return false;
    }
}

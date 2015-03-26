package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.UserDao;
import fr.epsi.tp.redtweet.exception.UserNotFound;
import fr.epsi.tp.redtweet.service.UserService;

import javax.annotation.Resource;

/**
 * Created by fx on 23/03/2015.
 */
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    public boolean auth(User user) {
        try {
            User userTmp = userDao.read(user.getUsername());
            return userTmp.getPassword().equals(userTmp.getPassword());
        } catch (UserNotFound e) {

        }
        return false;
    }
}

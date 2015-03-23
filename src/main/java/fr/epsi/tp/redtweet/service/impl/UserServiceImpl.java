package fr.epsi.tp.redtweet.service.impl;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.service.UserService;

/**
 * Created by fx on 23/03/2015.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return new User("fx", "hibon", "FXHibon");
    }
}

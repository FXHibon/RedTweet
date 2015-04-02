package fr.epsi.tp.redtweet.service;

import fr.epsi.tp.redtweet.bean.User;

/**
 * Created by fx on 23/03/2015.
 */
public interface UserService {

    /**
     * Authenticate given user
     *
     * @param user
     * @return
     */
    boolean auth(User user);
}

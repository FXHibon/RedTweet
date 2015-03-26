package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.exception.UserNotFound;

/**
 * Created by fx on 26/03/2015.
 */
public interface UserDao {

    boolean create(User user);

    User read(String userName) throws UserNotFound;

    boolean update(User user);

    boolean delete(User user);
}

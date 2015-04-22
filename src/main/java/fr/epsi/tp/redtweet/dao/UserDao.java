package fr.epsi.tp.redtweet.dao;

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.exception.UserNotFound;

import java.util.List;
import java.util.Map;

/**
 * Created by fx on 26/03/2015.
 */
public interface UserDao {

    boolean create(User user);

    User read(String userName) throws UserNotFound;

    boolean update(User user);

    boolean delete(User user);

    /**
     * Check of userName follows target
     *
     * @param username
     * @param target
     * @return
     */
    boolean isUserFollowing(String username, String target);

    List<Map> search(String query);
}

package fr.epsi.tp.redtweet.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fx on 23/03/2015.
 */
public class User {

    public static final String FIELD_FIRSTNAME = "first_name";
    public static final String FIELD_LASTNAME = "last_name";
    public static final String FIELD_USERNAME = "user_name";
    public static final String FIELD_PASSWORD = "password";

    private Map<String, String> map;

    public User() {
        map = new HashMap<String, String>();
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public User(Map<String, String> map) {
        this.map = map;
    }

    public String getFirstName() {
        return map.get(FIELD_FIRSTNAME);
    }

    public User setFirstName(String firstName) {
        map.put(FIELD_FIRSTNAME, firstName);
        return this;
    }

    public String getLastName() {
        return map.get(FIELD_LASTNAME);
    }

    public User setLastName(String lastName) {
        map.put(FIELD_LASTNAME, lastName);
        return this;
    }

    public String getUsername() {
        return map.get(FIELD_USERNAME);
    }

    public User setUsername(String username) {
        map.put(FIELD_USERNAME, username);
        return this;
    }

    public String getPassword() {
        return map.get(FIELD_PASSWORD);
    }

    public User setPassword(String password) {
        map.put(FIELD_PASSWORD, password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getMap().equals(user.getMap());

    }
}

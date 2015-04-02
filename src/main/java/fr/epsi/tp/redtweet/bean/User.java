package fr.epsi.tp.redtweet.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fx on 23/03/2015.
 */
public class User extends HashMap<String, String> {

    public static final String FIELD_FIRSTNAME = "firstName";
    public static final String FIELD_LASTNAME = "lastName";
    public static final String FIELD_USERNAME = "userName";
    public static final String FIELD_PASSWORD = "password";

    public User() {
    }

    public User(Map<String, String> map) {
        super(map);
    }

    public String getFirstName() {
        return this.get(FIELD_FIRSTNAME);
    }

    public User setFirstName(String firstName) {
        this.put(FIELD_FIRSTNAME, firstName);
        return this;
    }

    public String getLastName() {
        return this.get(FIELD_LASTNAME);
    }

    public User setLastName(String lastName) {
        this.put(FIELD_LASTNAME, lastName);
        return this;
    }

    public String getUsername() {
        return this.get(FIELD_USERNAME);
    }

    public User setUsername(String username) {
        this.put(FIELD_USERNAME, username);
        return this;
    }

    public String getPassword() {
        return this.get(FIELD_PASSWORD);
    }

    public User setPassword(String password) {
        this.put(FIELD_PASSWORD, password);
        return this;
    }
}

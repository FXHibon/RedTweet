package fr.epsi.tp.redtweet.bean;

/**
 * Created by fx on 23/03/2015.
 */
public class User {

    public final String FIELD_FIRSTNAME = "first_name";
    public final String FIELD_LASTNAME = "last_name";
    public final String FIELD_USERNAME = "user_name";
    public final String FIELD_PASSWORD = "password";

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

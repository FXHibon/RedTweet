package fr.epsi.tp.redtweet.bean;

import java.util.Map;

/**
 * Created by fhibon on 01/04/2015.
 */
public class Tweet {

    private Map<String, String> map;

    private String FIELD_AUTHOR = "author";
    private String FIELD_CONTENT = "content";
    private String FIELD_CREATED_AT = "createdAt";

    public Tweet(Map<String, String> tweetMap) {
        this.map = tweetMap;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public Tweet setMap(Map<String, String> map) {
        this.map = map;
        return this;
    }

    public String getAuthor() {
        return this.map.get(FIELD_AUTHOR);
    }

    public Tweet setAuthor(String author) {
        this.map.put(FIELD_AUTHOR, author);
        return this;
    }

    public String getContent() {
        return this.map.get(FIELD_CONTENT);
    }

    public Tweet setContent(String content) {
        this.map.put(FIELD_CONTENT, content);
        return this;
    }

    public Tweet setCreatedAt(String createdAt) {
        this.map.put(FIELD_CREATED_AT, createdAt);
        return this;
    }

    public String getCreatedAt() {
        return this.map.get(FIELD_CREATED_AT);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "map=" + map +
                '}';
    }
}

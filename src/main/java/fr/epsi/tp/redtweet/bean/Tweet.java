package fr.epsi.tp.redtweet.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fhibon on 01/04/2015.
 */
public class Tweet extends HashMap<String, String>{

    private String FIELD_ID = "id";
    private String FIELD_AUTHOR = "author";
    private String FIELD_CONTENT = "content";
    private String FIELD_CREATED_AT = "createdAt";

    public Tweet(Map<String, String> tweetMap) {
        super(tweetMap);
    }

    public String getAuthor() {
        return this.get(FIELD_AUTHOR);
    }

    public Tweet setAuthor(String author) {
        this.put(FIELD_AUTHOR, author);
        return this;
    }

    public String getContent() {
        return this.get(FIELD_CONTENT);
    }

    public Tweet setContent(String content) {
        this.put(FIELD_CONTENT, content);
        return this;
    }

    public Tweet setCreatedAt(String createdAt) {
        this.put(FIELD_CREATED_AT, createdAt);
        return this;
    }

    public String getCreatedAt() {
        return this.get(FIELD_CREATED_AT);
    }

    public String getId() {
        return this.get(FIELD_ID);
    }

    public Tweet setId(String id) {
        this.put(FIELD_ID, id);
        return this;
    }
}

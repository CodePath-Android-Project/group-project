package com.example.jobfinder;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze = Post.class)
@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public String formattedTime;

    //this is needed for Parcel
    public Post() { }

    public String getDescription() {
        return KEY_DESCRIPTION;
    }
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    //Parse already have the getCreatedAt method
    public String getKeyCreatedAt() {
        return getString(KEY_CREATED_AT);
    }
    public void setKeyCreatedAt(String created) {
        put(KEY_CREATED_AT, created);
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }
}
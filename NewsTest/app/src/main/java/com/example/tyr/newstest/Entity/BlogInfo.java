package com.example.tyr.newstest.Entity;

/**
 * Created by tyr on 2017/2/23.
 */
public class BlogInfo {

    private String id;
    private String title;
    private Author author;

    public BlogInfo() {
    }

    public BlogInfo(String id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

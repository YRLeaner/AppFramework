package com.example.tyr.newstest.Entity;

/**
 * Created by tyr on 2017/2/23.
 */
public class Author {

    private String name;
    private String link;
    private int image;
    private String blog_count;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String link, int image, String blog_count) {
        this.name = name;
        this.link = link;
        this.image = image;
        this.blog_count = blog_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBlog_count() {
        return blog_count;
    }

    public void setBlog_count(String blog_count) {
        this.blog_count = blog_count;
    }
}

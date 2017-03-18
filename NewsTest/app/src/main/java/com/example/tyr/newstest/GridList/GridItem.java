package com.example.tyr.newstest.GridList;

/**
 * Created by tyr on 2017/2/21.
 */
public class GridItem {

    private String title;
    private int img;

    public GridItem(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

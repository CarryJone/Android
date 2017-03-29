package com.gao.bryan.app_viewpager;

/**
 * Created by bryan on 2017/3/27.
 */

public class Date  {
    private String title;
    private String message;

    public Date(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

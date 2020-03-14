package com.example.administrator.demo22.Utils;

public class Title {
    public String title;
    public String description;
    public String source;
    public String picUrl;
    public String url;

    public Title(String title, String description, String source,String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.picUrl = picUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUrl() {
        return url;
    }
}

package com.example.android.guarianusari;

public class Stories {

    String title;
    String author;
    String url;
    String date;
    String section;

    public Stories(String title, String author, String url, String date, String section) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.date = date;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getSection() {
        return section;
    }
}

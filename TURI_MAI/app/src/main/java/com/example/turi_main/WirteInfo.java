package com.example.turi_main;

import java.util.Date;

public class WirteInfo {

    private String title;
    private String contents;
    private String publisher;

    public WirteInfo(String title, String contents, String publisher) {
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;

    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContents(){
        return this.contents;
    }
    public void setContents(String contents){
        this.contents = contents;
    }
    public String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
}

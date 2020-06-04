package com.example.korean_story;

public class Content {
    String title;
    String content;

    Content(){}

    Content(String title, String content){
        this.title = title;
        this.content = content;
    }
    public String getTitle(){return title;}
    public String getContent(){return content;}
}


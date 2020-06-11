package com.example.korean_story;

public class Collection {
    String name;
    String content;

    Collection(){}

    Collection(String name, String content){
        this.name = name;
        this.content = content;
    }

    public String getName(){return name;}
    public String getContent(){return content;}
}

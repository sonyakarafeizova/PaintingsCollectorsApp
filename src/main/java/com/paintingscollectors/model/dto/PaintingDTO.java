package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.StyleName;


public class PaintingDTO {
    private long id;
    private String username;
    private String name;
    private String author;
    private String imageUrl;
    private StyleName style;
    private int votes;

    public long getId() {
        return id;
    }

    public PaintingDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PaintingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PaintingDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PaintingDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleName getStyle() {
        return style;
    }

    public PaintingDTO setStyle(StyleName style) {
        this.style = style;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PaintingDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getVotes() {
        return votes;
    }

    public PaintingDTO setVotes(int votes) {
        this.votes = votes;
        return this;
    }
}

package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.StyleName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PaintingDTO {
    private Long id;
    @NotBlank
    @Size(min = 5, max = 40, message = "Name must be between 5 and 40 characters.")
    private String name;

    @NotBlank
    @Size(min = 5, max = 30, message = "Author must be between 5 and 30 characters.")
    private String author;

    @NotBlank(message = "You must select a style!")
    private StyleName styleName;

    @NotBlank(message = "Image URL is required.")
    @Size(max = 150, message = "Image URL must not exceed 150 characters.")
    private String imageUrl;

    private boolean isFavorite;

    @Min(value = 0, message = "Votes must be 0 or more.")
    private int votes;

    public PaintingDTO(Painting painting) {
        this.id = painting.getId();
        this.name = painting.getName();
        this.author = painting.getAuthor();
//        this.styleName = painting.getStyleName();
        this.imageUrl = painting.getImageUrl();
        this.isFavorite = painting.isFavorite();
        this.votes = painting.getVotes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStyleName() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleName styleName) {
        this.styleName = styleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}



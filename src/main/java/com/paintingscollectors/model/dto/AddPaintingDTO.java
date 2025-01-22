package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.StyleName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddPaintingDTO {

    @NotBlank(message = "Name is required.")
    @Size(min = 5, max = 40, message = "Name must be between 5 and 40 characters.")
    private String name;
    @NotBlank(message = "Author is required.")
    @Size(min = 5, max = 30, message = "Author must be between 5 and 30 characters.")
    private String author;

    private StyleName style;
    private String imageUrl;

    public AddPaintingDTO() {

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

    public StyleName getStyle() {
        return style;
    }

    public void setStyle(StyleName style) {
        this.style = style;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

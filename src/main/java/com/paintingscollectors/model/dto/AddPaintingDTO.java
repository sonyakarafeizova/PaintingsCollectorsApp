package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.StyleName;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaintingDTO {

    @NotBlank(message = "Name is required.")
    @Size(min = 5, max = 40, message = "Name must be between 5 and 40 characters.")
    private String name;
    @NotBlank(message = "Author is required.")
    @Size(min = 5, max = 30, message = "Author must be between 5 and 30 characters.")
    private String author;
    @NotNull(message = "You must select a style!")
    private StyleName styleName;

    @NotNull
    @Size(min=1, max = 150, message = "Please enter valid image URL!")
    private String imageUrl;


    public String getName() {
        return name;
    }

    public AddPaintingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public AddPaintingDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddPaintingDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public AddPaintingDTO setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }
}

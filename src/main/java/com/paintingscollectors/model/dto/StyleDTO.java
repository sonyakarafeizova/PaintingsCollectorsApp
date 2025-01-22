package com.paintingscollectors.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StyleDTO {
    @NotBlank(message = "Style name is required.")
    private String styleName;

    @NotBlank(message = "Description is required.")
    @Size(max = 500, message = "Description must not exceed 500 characters.")
    private String description;

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleName styleName;

    @Column(nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "style")
    private List<Painting> paintings;

    public Style(){
        this.paintings = new ArrayList<>();
    }
    public Style(StyleName styleName, String description) {
        this();

        this.styleName = styleName;
        this.description = description;

    }

    public StyleName getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleName styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }
}
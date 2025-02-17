package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.BaseEntity;

import javax.persistence.*;



@Entity
@Table(name = "styles")
public class Style extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleName styleName;

    @Column(columnDefinition = "TEXT")
    private String description;


    public Style() {

    }
    public Style(StyleName styleName) {
        this.setName(styleName);
    }

    public StyleName getName() {
        return styleName;
    }

    public Style setName(StyleName styleName) {
        this.styleName=styleName;
        this.setDescription(styleName);
        return this;
    }
    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }

    private void setDescription(StyleName styleName) {
        String description = "";
        switch (styleName) {
            case IMPRESSIONISM:
                description = "Impressionism is a painting style most commonly associated with the 19th century where " +
                        "small brush strokes are used to build up a larger picture.";
                break;
            case ABSTRACT:
                description = "Abstract art does not attempt to represent recognizable subjects in a realistic manner.";
                break;
            case EXPRESSIONISM:
                description = "Expressionism is a style of art that doesn't concern itself with realism.";
                break;
            case SURREALISM:
                description = "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.";
                break;
            case REALISM:
                description = "Also known as naturalism, this style of art is considered as 'real art' and has been" +
                        " the dominant style of painting since the Renaissance.";
                break;
        }
        this.description = description;
    }

}
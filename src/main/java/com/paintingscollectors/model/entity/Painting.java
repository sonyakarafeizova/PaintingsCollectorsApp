package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "paintings")
public class Painting extends BaseEntity {
   @Column(nullable = false)
    private String name;
   @Column(nullable = false)
   private String author;
   @ManyToOne
   @JoinColumn(nullable = false)
   private Style style;

   @ManyToOne
   @JoinColumn( nullable = false)
   private User owner;

   @Column(nullable = false, length = 150)
   private String imageUrl;

   @Column(nullable = false)
   private boolean isFavorite;

   @Column(nullable = false)
   private int votes;
   @ManyToOne(optional = false)
   private User addedBy;
   public Painting() {

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

   public Style getStyle() {
      return style;
   }

   public void setStyle(Style style) {
      this.style = style;
   }

   public User getOwner() {
      return owner;
   }

   public void setOwner(User owner) {
      this.owner = owner;
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

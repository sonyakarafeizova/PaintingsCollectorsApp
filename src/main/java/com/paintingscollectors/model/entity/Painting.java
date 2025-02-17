package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "paintings")
public class Painting extends BaseEntity {
   @Column(nullable = false)
   private String name;
   @Column(nullable = false)
   private String author;
   @ManyToOne(fetch = FetchType.EAGER)
   private Style style;

   @ManyToOne(optional = false)
   private User owner;

   @Column(nullable = false)
   private String imageUrl;

   @Column(nullable = false)
   private boolean isFavorite;

   @Column(nullable = false)
   private int votes;

   public String getName() {
      return name;
   }

   public Painting setName(String name) {
      this.name = name;
      return this;
   }

   public String getAuthor() {
      return author;
   }

   public Painting setAuthor(String author) {
      this.author = author;
      return this;
   }

   public Style getStyle() {
      return style;
   }

   public Painting setStyle(Style style) {
      this.style = style;
      return this;
   }

   public User getOwner() {
      return owner;
   }

   public Painting setOwner(User owner) {
      this.owner = owner;
      return this;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public Painting setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
      return this;
   }

   public boolean isFavorite() {
      return isFavorite;
   }

   public Painting setFavorite(boolean favorite) {
      isFavorite = favorite;
      return this;
   }

   public int getVotes() {
      return votes;
   }

   public Painting setVotes(int votes) {
      this.votes = votes;
      return this;
   }
}

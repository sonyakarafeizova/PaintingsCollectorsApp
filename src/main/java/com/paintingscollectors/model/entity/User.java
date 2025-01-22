package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Painting> paintings;
    @ManyToMany
    private Set<Painting> favoritePaintings = new HashSet <>();

    @ManyToMany
    private Set<Painting> ratedPaintings = new HashSet<>();

    public User() {
this.paintings = new HashSet<>();
        this.favoritePaintings = new HashSet<>();
        this.ratedPaintings = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(Set<Painting> paintings) {
        this.paintings = paintings;
    }

    public Set<Painting> getFavoritePaintings() {
        return favoritePaintings;
    }

    public void setFavoritePaintings(Set<Painting> favoritePaintings) {
        this.favoritePaintings = favoritePaintings;
    }

    public Set<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public void setRatedPaintings(Set<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
    }

    public void addFavorite(Painting painting) {
        this.favoritePaintings.add(painting);
    }
}

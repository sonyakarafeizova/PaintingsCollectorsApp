package com.paintingscollectors.vallidation;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.service.PaintingService;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

public class LoggedUser {
    private Long id;
    private String username;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Painting> favouritePaintings;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Painting> likedPaintings;

    private final PaintingService paintingService;

    public LoggedUser(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLogged() {
        return this.username != null && this.id != null;
    }

    public Set<Painting> getFavouritePaintings() {
        return favouritePaintings;
    }

    public boolean isPaintingFavorite(Long paintingId) {
        return paintingService.isPaintingFavorite(paintingId, getId());
    }


    public boolean isPaintingLikedByUser(Long paintingId) {
        List<Painting> likedPaintings = paintingService.getAllLikedPaintingsByUser(getId());
        return likedPaintings.stream().anyMatch(painting -> painting.getId().equals(paintingId));
    }


    public void setFavouritePaintings(Set<Painting> favouritePaintings) {
        this.favouritePaintings = favouritePaintings;
    }

    public Set<Painting> getLikedPaintings() {
        return likedPaintings;
    }

    public void setLikedPaintings(Set<Painting> likedPaintings) {
        this.likedPaintings = likedPaintings;
    }


}

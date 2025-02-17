package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.entity.Painting;

import java.util.List;
import java.util.Set;

public interface PaintingService {
    void paintingCreate(AddPaintingDTO createPanting);

    Painting getPaintingById(long paintingId);
    Set<PaintingDTO> addToMyFavoritesList(long paintingId);

    void removePaintingFromFavorites(long paintingId);

    void vote(long paintingId);
    List<PaintingDTO> getAllPaintingsByRate();

    void remove(long id);
}

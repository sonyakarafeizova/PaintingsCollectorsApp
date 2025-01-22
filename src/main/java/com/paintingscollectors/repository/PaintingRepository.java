package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PaintingRepository extends JpaRepository<Painting,Long> {
    List<Painting> findAllByStyle(Style style);

    boolean existsByName(String name);


}

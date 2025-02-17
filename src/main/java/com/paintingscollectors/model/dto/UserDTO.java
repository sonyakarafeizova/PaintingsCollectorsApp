package com.paintingscollectors.model.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private long id;
    private String username;
    List<PaintingDTO> paintingList;

    public UserDTO() {
        this.paintingList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public UserDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<PaintingDTO> getPaintingList() {
        return paintingList;
    }

    public UserDTO setPaintingList(List<PaintingDTO> paintingList) {
        this.paintingList = paintingList;
        return this;
    }
}

package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.dto.UserDTO;
import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;
import com.paintingscollectors.model.entity.User;

import java.util.Set;

public interface UserService {
    boolean getUniqueUsername(String username);

    boolean getUniqueEmail(String email);

    void registerUser(UserRegisterDTO registerDto);

    boolean loginUser(UserLoginDTO loginDto);

    void logoutUser();

    boolean isLoggedUser();

    User getLoggedUser();

    Set<PaintingDTO> getAllPaintingsOfLoggedUser();

    Set<UserDTO> getAllPaintingsOfOthersUsers();
    Set<User> getAllFavoritePaintingsOfOthersUsers();

    Set<User> getNotLoggedUsers(long userid);

    Set<PaintingDTO> getMyFavoritesList();


}

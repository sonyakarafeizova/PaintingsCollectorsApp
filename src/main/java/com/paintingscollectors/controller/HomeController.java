package com.paintingscollectors.controller;


import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.dto.UserDTO;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import java.util.Set;
@Controller
public class HomeController {
    private final UserService userService;
    private final PaintingService paintingService;

    @Autowired
    public HomeController(UserService userService, PaintingService paintingService) {
        this.userService = userService;
        this.paintingService = paintingService;
    }

    @GetMapping("/")
    public String index() {
        if (this.userService.isLoggedUser()) {
            return Constant.REDIRECT_HOME;
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        Set<PaintingDTO> userAllPaintings = this.userService.getAllPaintingsOfLoggedUser();
        model.addAttribute("userPaintings", userAllPaintings);

        Set<UserDTO> otherUsers = this.userService.getAllPaintingsOfOthersUsers();
        model.addAttribute("otherUsersPaintings", otherUsers);

        Set<PaintingDTO> myFavoriteList = this.userService.getMyFavoritesList();
        model.addAttribute("myFavorites", myFavoriteList);

        List<PaintingDTO> allVotedPaintings = this.paintingService.getAllPaintingsByRate();
        model.addAttribute("allVotedPaintings", allVotedPaintings);
        return "home";
    }

}

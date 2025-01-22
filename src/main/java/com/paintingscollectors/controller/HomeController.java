package com.paintingscollectors.controller;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.StyleName;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static org.modelmapper.Converters.Collection.map;

@Controller
public class HomeController {
    private final UserService userService;
    private final PaintingService paintingService;
    private final UserSession userSession;

    public HomeController(UserService userService,
                          PaintingService paintingService, UserSession userSession) {
        this.userService = userService;
        this.paintingService = paintingService;
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String nonLoggedIndex() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String loggedIndex(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        Map<StyleName, List<Painting>> allPaintings = paintingService.findAllByStyle();

        List<PaintingDTO> favourites = userService.findFavorites(userSession.id())
                .stream()
                .map(PaintingDTO::new)
                .toList();

        List<PaintingDTO> abstracts = allPaintings.get(StyleName.ABSTRACT)
                .stream()
                .map(PaintingDTO::new)
                .toList();

        List<PaintingDTO> impressionism = allPaintings.get(StyleName.IMPRESSIONISM)
                .stream()
                .map(PaintingDTO::new)
                .toList();

        List<PaintingDTO> realism = allPaintings.get(StyleName.REALISM)
                .stream()
                .map(PaintingDTO::new)
                .toList();
        List<PaintingDTO> expressionism = allPaintings.get(StyleName.EXPRESSIONISM)
                .stream()
                .map(PaintingDTO::new)
                .toList();
        List<PaintingDTO> surrealism = allPaintings.get(StyleName.SURREALISM)
                .stream()
                .map(PaintingDTO::new)
                .toList();

        model.addAttribute("abstractsData", abstracts);
        model.addAttribute("impressionismData", impressionism);
        model.addAttribute("realismData", realism);
        model.addAttribute("expressionismData", expressionism);
        model.addAttribute("surrealismData", surrealism);
        model.addAttribute("favouritesData", favourites);


        return "home";
    }


}

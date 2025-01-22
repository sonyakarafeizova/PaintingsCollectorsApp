package com.paintingscollectors.controller;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PaintingController {
    private final PaintingService paintingService;
    private final UserSession userSession;

    public PaintingController(PaintingService paintingService, UserSession userSession) {
        this.paintingService = paintingService;
        this.userSession = userSession;
    }

    @ModelAttribute("paintingData")
    public AddPaintingDTO paintingData() {
        return new AddPaintingDTO();
    }

    @GetMapping("/add-painting")
    public String addPainting() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "painting-add";
    }

    @PostMapping("/add-painting")
    public String doAddPainting(
            @Valid AddPaintingDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("paintingData", data);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.paintingData", bindingResult);
            return "redirect:/add-painting";
        }

        boolean success = paintingService.create(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("paintingData", data);
            return "redirect:/add-painting";
        }
        return "redirect:/home";
    }
    @PostMapping("/add-to-favourites/{paintingId}")
    public String addToFavourites(@PathVariable long paintingId) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        paintingService.addFavorite(userSession.id(), paintingId);

        return "redirect:/home";
    }


}

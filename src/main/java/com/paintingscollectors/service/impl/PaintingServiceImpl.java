package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleName;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaintingServiceImpl implements PaintingService {
    private final PaintingRepository paintingRepository;
    private final UserRepository userRepository;
     private final UserServiceImpl userService;


    private final StyleServiceImpl styleService;



    public PaintingServiceImpl(PaintingRepository paintingRepository, UserRepository userRepository,
                               UserServiceImpl userService, StyleServiceImpl styleService) {
        this.paintingRepository = paintingRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.styleService = styleService;
    }


    @Override
    public void paintingCreate(AddPaintingDTO createPanting) {
        Painting painting = this.mapPaintingDtoToPainting(createPanting);
        User loggedUser = this.loggedUser();
        loggedUser.getPaintings().add(painting);
        painting.setOwner(loggedUser);
        this.paintingRepository.save(painting);
        this.userRepository.save(loggedUser);
    }

    @Override
    @Transactional
    public Set<PaintingDTO> addToMyFavoritesList(long paintingId) {
        Painting painting = this.getPaintingById(paintingId);
        User user = this.loggedUser();
        user.getFavoritePaintings().add(painting);
        painting.setFavorite(true);
        this.userRepository.save(user);
        return user.getFavoritePaintings().stream()
                .map(this::mapPaintingToPaintingDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void removePaintingFromFavorites(long paintingId) {
        Painting painting = this.getPaintingById(paintingId);
        User user = this.loggedUser();
        user.getFavoritePaintings().remove(painting);
        this.userRepository.save(user);
        if (this.checkPaintingIsNotFavorite(painting)) {
            painting.setFavorite(false);
        }
    }

    @Override
    @Transactional
    public void vote(long paintingId) {
        Painting painting = this.getPaintingById(paintingId);
        User user = this.loggedUser();
        if (!user.getRatedPaintings().contains(painting)) {
            int vote = painting.getVotes() + 1;
            painting.setVotes(vote);
            user.getRatedPaintings().add(painting);
        }
    }

    @Override
    public Painting getPaintingById(long paintingId) {
        return this.paintingRepository.findById(paintingId)
                .orElseThrow(() -> new RuntimeException("Not Found Painting"));
    }

    @Override
    public List<PaintingDTO> getAllPaintingsByRate() {
        int minVote = 1;
        List<Painting> allRatedPaintings = this.paintingRepository.findAllByVotesGreaterThanEqual(minVote);

        return allRatedPaintings.stream()
                .sorted((p1, p2) -> {
                    int sort = Integer.compare(p2.getVotes(), p1.getVotes());
                    if (sort == 0) {
                        sort = p1.getName().compareTo(p2.getName());
                    }
                    return sort;

                })
                .limit(2)
                .map(this::mapPaintingToPaintingDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(long id) {
        Painting painting = this.getPaintingById(id);
        if (!painting.isFavorite()) {
            List<User> getAllUsers = this.userRepository.findAll();
            getAllUsers.forEach(user -> {
                user.getRatedPaintings().remove(painting);
            });
            this.userRepository.saveAll(getAllUsers);
            User user = this.loggedUser();
            user.getPaintings().remove(painting);
            this.userRepository.save(user);
            painting.getOwner().getRatedPaintings().remove(painting);
            this.paintingRepository.delete(painting);
        }
    }

    private boolean checkPaintingIsNotFavorite(Painting painting) {
        Set<User> allOthersUsers = this.userService.getAllFavoritePaintingsOfOthersUsers();
        return allOthersUsers.stream()
                .filter(user -> user.getFavoritePaintings().contains(painting))
                .collect(Collectors.toSet()).isEmpty();

    }

    private PaintingDTO mapPaintingToPaintingDto(Painting painting) {
        return new PaintingDTO()
                .setId(painting.getId())
                .setName(painting.getName())
                .setUsername(painting.getOwner().getUsername())
                .setAuthor(painting.getAuthor())
                .setStyle(painting.getStyle().getName())
                .setImageUrl(painting.getImageUrl())
                .setVotes(painting.getVotes());
    }

    private Painting mapPaintingDtoToPainting(AddPaintingDTO createPainting) {
        Style style = this.styleService.getStyleByStyleName(createPainting.getStyleName());
        return new Painting()
                .setName(createPainting.getName())
                .setAuthor(createPainting.getAuthor())
                .setImageUrl(createPainting.getImageUrl())
                .setStyle(style);
    }

    private User loggedUser() {
        return this.userService.getLoggedUser();
    }
}

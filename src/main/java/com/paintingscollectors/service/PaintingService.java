package com.paintingscollectors.service;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleName;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PaintingService {
    private final PaintingRepository paintingRepository;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository ;
    private final UserSession userSession;


    public PaintingService(PaintingRepository paintingRepository, UserRepository userRepository,
                           StyleRepository styleRepository, UserSession userSession) {
        this.paintingRepository = paintingRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
        this.userSession = userSession;
    }

    public boolean create (AddPaintingDTO data) {
        if (!userSession.isLoggedIn()) {
            return false;
        }
        Optional<User> byId = userRepository.findById(userSession.id());

        if (byId.isEmpty()) {
            return false;

        }
        Optional<Style> byName = styleRepository.findByStyleName(data.getStyle());
        if (byName.isEmpty()) {
            return false;
        }

        Painting painting = new Painting();
        painting.setName(data.getName());
        painting.setAuthor(data.getAuthor());
        painting.setStyle(byName.get());
        painting.setOwner(byId.get());

        paintingRepository.save(painting);

        return true;
    }

    public Map<StyleName, List<Painting>> findAllByStyle() {

        Map<StyleName,List<Painting>> result=new HashMap<>();

        List<Style> allStyles = styleRepository.findAll();

        for(Style st:allStyles){
            List<Painting> paintings=paintingRepository.findAllByStyle(st);

            result.put(st.getStyleName(),paintings);
        }


        return result;
    }
    @Transactional
    public void addFavorite(Long id, long paintingId) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return;
        }

        Optional<Painting> paintingOpt = paintingRepository.findById(paintingId);

        if (paintingOpt.isEmpty()) {
            return;
        }
        userOpt.get().addFavorite(paintingOpt.get());
        userRepository.save(userOpt.get());
    }

    public boolean isPaintingFavorite(Long paintingId, Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return false;
        }

        Optional<Painting> paintingOpt = paintingRepository.findById(paintingId);

        if (paintingOpt.isEmpty()) {
            return false;
        }

        return userOpt.get().getFavoritePaintings().contains(paintingOpt.get());
    }

    public List<Painting> getAllLikedPaintingsByUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return List.of();
        }

        return (List<Painting>) userOpt.get().getFavoritePaintings();
    }
}

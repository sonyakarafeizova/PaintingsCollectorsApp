package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleName;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.service.StyleService;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;


    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyles() {
        if (this.styleRepository.count() == 0) {
            List<Style> styleList = Arrays.stream(StyleName.values())
                    .map(Style::new)
                    .toList();
            this.styleRepository.saveAll(styleList);

        }

    }

    @Override
    public Style getStyleByStyleName(StyleName styleName) {
        return this.styleRepository.findByStyleName(styleName);
    }
}

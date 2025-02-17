package com.paintingscollectors.service;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleName;

public interface StyleService {
    void initStyles();

    Style getStyleByStyleName(StyleName styleName);

}

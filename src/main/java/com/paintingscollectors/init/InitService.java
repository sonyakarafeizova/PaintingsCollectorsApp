package com.paintingscollectors.init;


import com.paintingscollectors.service.impl.StyleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class InitService implements CommandLineRunner {
    private final StyleServiceImpl styleService;

    public InitService(StyleServiceImpl styleService) {
        this.styleService = styleService;
    }


    @Override
    public void run(String... args) throws Exception {
       //this.styleService.initStyles();


    }
}
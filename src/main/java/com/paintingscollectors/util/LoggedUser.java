package com.paintingscollectors.util;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.service.impl.PaintingServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Component
@SessionScope
public class LoggedUser {
    private long id;
    private String username;
    private boolean isLogged;

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.isLogged = true;
    }
    public void logout() {
        this.id = 0;
        this.username = null;
        this.isLogged = false;
    }

    public long getId() {
        return id;
    }

    public LoggedUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public LoggedUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }


}

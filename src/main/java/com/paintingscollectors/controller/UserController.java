package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.service.impl.UserServiceImpl;
import com.paintingscollectors.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDto")
    public UserRegisterDTO initRegisterUser() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginDto")
    public UserLoginDTO initLoginDto(){
        return new UserLoginDTO();
    }

    @ModelAttribute("badCredentials")
    public void badCredentials(Model model) {
        model.addAttribute(Constant.BAD_CREDENTIALS);
    }


    @GetMapping("/register")
    public String getRegister() {
        if (this.userService.isLoggedUser()) {
            return Constant.REDIRECT_HOME;
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterDTO registerDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (this.userService.isLoggedUser()) {
            return Constant.REDIRECT_HOME;
        }
        boolean isMatchedPassword = registerDto.getPassword().equals(registerDto.getConfirmPassword());
        if (!isMatchedPassword) {
            bindingResult.addError(new FieldError(
                    Constant.OBJECT_DIFFERENT_CONFIRM_PASSWORD,
                    Constant.FIELD_CONFIRM_PASSWORD,
                    Constant.DEFAULT_MESSAGE));
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(Constant.REGISTER_ATTRIBUTE_NAME, registerDto)
                    .addFlashAttribute(Constant.REGISTER_BINDING_RESULT_NAME, bindingResult);
            return Constant.REDIRECT_REGISTER;
        }
        this.userService.registerUser(registerDto);
        return Constant.REDIRECT_LOGIN;
    }

    @GetMapping("/login")
    public String getLogin() {
        if (this.userService.isLoggedUser()) {
            return Constant.REDIRECT_HOME;
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginDTO loginDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (this.userService.isLoggedUser()) {
            return Constant.REDIRECT_HOME;
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(Constant.LOGIN_ATTRIBUTE_NAME, loginDto)
                    .addFlashAttribute(Constant.LOGIN_BINDING_RESULT_NAME, bindingResult);
            return Constant.REDIRECT_LOGIN;
        }
        if (!this.userService.loginUser(loginDto)) {
            redirectAttributes
                    .addFlashAttribute(Constant.LOGIN_ATTRIBUTE_NAME, loginDto)
                    .addFlashAttribute(Constant.BAD_CREDENTIALS, true);
            return Constant.REDIRECT_LOGIN;
        }
        return Constant.REDIRECT_HOME;
    }

    @GetMapping("/logout")
    public String logout() {
        if (!this.userService.isLoggedUser()) {
            return Constant.REDIRECT_LOGIN;
        }
        this.userService.logoutUser();
        return Constant.REDIRECT_LOGOUT;
    }
}

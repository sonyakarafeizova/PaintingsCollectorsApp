package com.paintingscollectors.util;

public class Constant {
    public static final String REGISTER_ATTRIBUTE_NAME = "registerDto";
    public static final String REGISTER_BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.registerDto";
    public static final String LOGIN_ATTRIBUTE_NAME = "loginDto";
    public static final String LOGIN_BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.loginDto";
    public static final String PAINTING_ATTRIBUTE_NAME = "createPainting";
    public static final String PAINTING_BINDING_RESULT_NAME = "org.springframework.validation.BindingResult.createPainting";
    public static final String BAD_CREDENTIALS = "badCredentials";



    public static final String REDIRECT_LOGIN = "redirect:/users/login";
    public static final String REDIRECT_LOGOUT = "redirect:/";
    public static final String REDIRECT_REGISTER = "redirect:/users/register";
    public static final String REDIRECT_HOME = "redirect:/home";
    public static final String REDIRECT_PAINTING_ADD = "redirect:/paintings/add-painting";



    public static final String OBJECT_DIFFERENT_CONFIRM_PASSWORD = "differentConfirmPassword";
    public static final String FIELD_CONFIRM_PASSWORD = "confirmPassword";
    public static final String DEFAULT_MESSAGE = "Passwords must be the same.";

}

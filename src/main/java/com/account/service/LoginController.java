package com.account.service;


import com.account.forms.LoginForm;
import com.account.models.RegisteredUser;
import com.account.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class LoginController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/login")
    public String login(LoginForm loginForm){
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return "login";
        }
        RegisteredUser registeredUser = registrationRepository.findByUserNameAndPassword(loginForm.getUserName(), loginForm.getPassword());

//        If there is a matching username and password in the registered users table then log in
        if (!Objects.isNull(registeredUser)){
            log.info(registeredUser.getUserName());
            return "success";
        } else {
            return "failed";
        }
    }

}

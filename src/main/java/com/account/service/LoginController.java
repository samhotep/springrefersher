package com.account.service;


import com.account.forms.LoginForm;
import com.account.models.RegisteredUser;
import com.account.models.Response;
import com.account.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class LoginController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @PostMapping("/login")
    public Response login(@RequestBody @Valid LoginForm loginForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            for (ObjectError error: bindingResult.getAllErrors()){
                log.info(error.toString());
            }
            return new Response(400, "ERROR", "Errors List");
        }
        RegisteredUser registeredUser = registrationRepository.findByUserNameAndPassword(loginForm.getUserName(), loginForm.getPassword());

//        If there is a matching username and password in the registered users table then log in
        if (!Objects.isNull(registeredUser)){
            log.info(registeredUser.getUserName());
            return new Response(1012, "SUCCESS","User is Valid");
        } else {
            return new Response(1013, "FAILURE","User is invalid");
        }
    }

}

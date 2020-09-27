package com.account.service;

import com.account.forms.UserForm;
import com.account.models.RegisteredUser;
import com.account.models.User;
import com.account.repository.RegistrationRepository;
import com.account.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Objects;

@Controller
public class RegistrationController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register(UserForm userForm) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserForm userForm, BindingResult bindingResult){

        for (ObjectError error: bindingResult.getAllErrors()){
            log.info(error.toString());
        }
        if (bindingResult.hasErrors()){
            return "register";
        }

        RegisteredUser registeredUser = registrationRepository.findByIdNumber(Integer.parseInt(userForm.getIdNumber()));
//        If the validation succeeds, then we check the registration table for the idnumber and username
        if (Objects.isNull(registeredUser)){
//            If the user is a new one, then we create a new user, and a new entry in the registereduser table
            String countrySuffix;
            switch (userForm.getCountry()){
                case "uganda":
                    countrySuffix = "254";
                case "tanzania":
                    countrySuffix = "255";
                case "kenya":
                    countrySuffix = "256";
                default:
                    countrySuffix = "255";
            }
            userRepository.save(new User(
                    userForm.getUserName(),
                    Integer.parseInt(userForm.getIdNumber()),
                    userForm.getIdType(),
                    userForm.getFirstName(),
                    userForm.getMiddleName(),
                    userForm.getLastName(),
                    Long.parseLong(countrySuffix + userForm.getPhoneNumber().substring(2)),
                    userForm.getCountry(),
                    Date.valueOf(userForm.getDob()),
                    userForm.getPassword()
            ));

            registrationRepository.save(new RegisteredUser(
                userForm.getUserName(),
                Integer.parseInt(userForm.getIdNumber()),
                userForm.getPassword()
            ));
            log.info("Created New User!");
            return "redirect:/success";
        } else {
            log.info("User already Exists!");
            return "redirect:/failed";
        }

    }

}



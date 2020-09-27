package com.account.service;

import com.account.forms.UserForm;
import com.account.models.RegisteredUser;
import com.account.models.Response;
import com.account.models.User;
import com.account.repository.RegistrationRepository;
import com.account.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Objects;

@RestController
public class RegistrationController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;
    private ObjectError error;

    @PostMapping("/register")
    public @ResponseBody Response registerUser(@RequestBody @Valid UserForm userForm, BindingResult bindingResult){


        for (ObjectError error: bindingResult.getAllErrors()){
            log.info(error.getCodes()[1]);
            log.info(error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()){
            return new Response(400, "ERROR", "Errors List", bindingResult.getAllErrors());
        }

        String countrySuffix;
        switch (userForm.getCountry()){
            case "tanzania":
                countrySuffix = "255";
            case "kenya":
                countrySuffix = "254";
            default:
                countrySuffix = "254";
        }
        String suffixPhoneNumber = countrySuffix + userForm.getPhoneNumber().substring(1);

        RegisteredUser registeredUserName = registrationRepository.findByUserName(userForm.getUserName());
        RegisteredUser registeredIdNumber = registrationRepository.findByIdNumber(Integer.parseInt(userForm.getIdNumber()));
        User userPhoneNumber = userRepository.findByPhoneNumber(Long.parseLong(suffixPhoneNumber));

//        If the validation succeeds, then we check the registration table for the idnumber and username
        if (Objects.isNull(registeredUserName) && Objects.isNull(registeredIdNumber) && Objects.isNull(userPhoneNumber)){
//            If the user is a new one, then we create a new user, and a new entry in the registereduser table
            userRepository.save(new User(
                    userForm.getUserName(),
                    Integer.parseInt(userForm.getIdNumber()),
                    userForm.getIdType(),
                    userForm.getFirstName(),
                    userForm.getMiddleName(),
                    userForm.getLastName(),
                    Long.parseLong(suffixPhoneNumber),
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
            return new Response(1012, "SUCCESS","ID is Valid");
        } else {
            log.info("User already Exists!");
            return new Response(1101, "FAILED", "User already exists");
        }

    }

}



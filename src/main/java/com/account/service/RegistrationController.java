package com.account.service;

import com.account.forms.UserForm;
import com.account.models.RegisteredUser;
import com.account.models.Response;
import com.account.models.IPRSUser;
import com.account.repository.RegistrationRepository;
import com.account.repository.IPRSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Map;
import java.util.Objects;

@RestController
public class RegistrationController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private IPRSRepository iprsRepository;
    private ObjectError error;

    @PostMapping("/IPRSValidation")
    public @ResponseBody Response validate(@RequestBody Map<String, String> itemsMap){
        String idNumber = itemsMap.get("idNumber");
        IPRSUser IPRSUser = iprsRepository.findByIdNumber(Integer.parseInt(idNumber));
        RegisteredUser registeredUser = registrationRepository.findByIdNumber(Integer.parseInt(idNumber));
        if (Objects.isNull(IPRSUser)){
            return new Response(400, "INVALID", "ID is not valid");
        } else if (!Objects.isNull(registeredUser)) {
            return new Response(401, "INVALID", "ID is already registered.");
        } else {
            return new Response(200, "VALIDATED", IPRSUser);
        }
    }

    @PostMapping("/register")
    public @ResponseBody Response registerUser(@RequestBody @Valid UserForm userForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            for (ObjectError error: bindingResult.getAllErrors()){
                log.info(error.toString());
            }
            return new Response(420, "ERROR", "Input values are incorrect", bindingResult.getAllErrors());
        }

        RegisteredUser registeredUser = registrationRepository.findByIdNumber(Integer.parseInt(userForm.getIdNumber()));

//        If the validation succeeds, then we check the registration table for the idnumber and username
        if (Objects.isNull(registeredUser)){
//            If the user is a new one, then we create a new user, and a new entry in the registereduser table
            registrationRepository.save(new RegisteredUser(
                    Integer.parseInt(userForm.getIdNumber()),
                    userForm.getIdType(),
                    userForm.getUserName(),
                    userForm.getPassword(),
                    userForm.getFirstName(),
                    userForm.getMiddleName(),
                    userForm.getLastName(),
                    userForm.getPhoneNumber(),
                    userForm.getCountry(),
                    Date.valueOf(userForm.getDob())
            ));

            log.info("Created New User!");
            return new Response(1012, "SUCCESS","ID is Valid");
        } else {
            log.info("User already Exists!");
            return new Response(400, "INVALID", "ID is already registered.");
        }

    }

}

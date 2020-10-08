package com.account.service;

import com.account.forms.LoginForm;
import com.account.forms.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/login")
    public String getLogin(LoginForm loginForm){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(UserForm userForm)
    {
        return "register";
    }

    @GetMapping("/failure")
    public String getFailure(UserForm userForm){
        return "failure";
    }

    @GetMapping("/success")
    public String getSuccess(UserForm userForm){
        return "success";
    }

}

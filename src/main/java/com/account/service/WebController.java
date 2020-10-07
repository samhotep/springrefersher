package com.account.service;

import com.account.forms.LoginForm;
import com.account.forms.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WebController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/login")
    public String getLogin(LoginForm loginForm, HttpServletResponse response){

        Cookie cookie = new Cookie("Brian", "Wairimu");
        Cookie cookie2 = new Cookie("Joan", "Wangui");
        Cookie cookie3 = new Cookie("Sam", "Olwe");
        response.addCookie(cookie);
        response.addCookie(cookie2);
        response.addCookie(cookie3);

        return "login";
    }

    @GetMapping("/register")
    public String getRegister(UserForm userForm,  HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies
             ) {
            log.info(cookie.getName() + " = " + cookie.getValue());
        }
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

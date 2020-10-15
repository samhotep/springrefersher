package com.account.service;

import com.account.forms.LoginForm;
import com.account.forms.UserForm;
import com.account.models.Administrator;
import com.account.models.Permissions;
import com.account.models.RegisteredUser;
import com.account.repository.AdministratorRepository;
import com.account.repository.PermissionsRepo;
import com.account.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;

@Controller
public class WebController implements WebMvcConfigurer {

    private RegisteredUser validRegisteredUser = null;
    private Administrator validAdministratorUser = null;

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private PermissionsRepo permissionsRepo;

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

    @GetMapping("/admin")
    public String getAdmin(UserForm userForm, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = "";
        String token = "";
        try {
            for (Cookie cookie: cookies
            ) {
                if (cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                } else if (cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
            }

            if (userIsValid(userName, token)){
                if (hasPermission("access.adminpage") || !Objects.isNull(validAdministratorUser)){
                    return "admin";
                }
            }
        } catch (NullPointerException e) {}
        return "login";
    }

    @GetMapping("/failure")

    public String getFailure(UserForm userForm){
        return "failure";
    }

    @GetMapping("/success")
    public String getSuccess(UserForm userForm, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userName = null;
        String token = null;
        for (Cookie cookie: cookies
             ) {
            if (cookie.getName().equals("userName")){
                userName = cookie.getValue();
            } else if (cookie.getName().equals("token")){
                token = cookie.getValue();
            }
        }

        if (userIsValid(userName, token)){
            return "success";
        }
        return "failure";
    }

    public Boolean userIsValid(String userName, String token){
        RegisteredUser registeredUser = registrationRepository.findByUserNameAndToken(userName, token);
        if (Objects.isNull(registeredUser)){
            Administrator administrator = administratorRepository.findByUserNameAndToken(userName, token);
            if (Objects.isNull(administrator)){
                return false;
            }
            this.validAdministratorUser = administrator;
        }
        this.validRegisteredUser = registeredUser;
        return true;
    }

    public Boolean hasPermission(String permission){
        if (!Objects.isNull(validAdministratorUser)){
            Set<Permissions> permissions = permissionsRepo.findByAdministrator(validAdministratorUser);
            for (Permissions perm: permissions
            ) {
                if (perm.getName().equals(permission)){
                    return true;
                }
            }
        }
        return false;
    }

}

package com.account.service;

import com.account.models.Administrator;
import com.account.models.Permissions;
import com.account.models.RegisteredUser;
import com.account.models.Response;
import com.account.repository.AdministratorRepository;
import com.account.repository.PermissionsRepo;
import com.account.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
public class AdminController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private RegisteredUser validRegisteredUser = null;
    private Administrator validAdministratorUser = null;
    private String userName = "";
    private String token = "";

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private PermissionsRepo permissionsRepo;

    @GetMapping("/admin/admins")
    public Response getAdmins(HttpServletRequest request){
        getCookies(request);
        if (userIsValid() && hasPermission("access.adminslist")){
            List<Administrator> administrators;
            if (validAdministratorUser.getRole().equals("admin")){
                administrators = administratorRepository.findAll();
            } else {
                administrators = administratorRepository.findByRole(validAdministratorUser.getRole());
            }
            return new Response(200, "VALIDATED", administrators);
        }
        return new Response(1013, "FAILURE","You do not have the required permissions to access this resource.");
    }

    @GetMapping("/admin/users")
    public Response getUsers(HttpServletRequest request){
        getCookies(request);
        if (userIsValid() && hasPermission("access.userslist")){
            List<RegisteredUser> registeredUsers;
            registeredUsers = registrationRepository.findAll();
            return new Response(200, "VALIDATED", 5, registeredUsers);
        }
        return new Response(1013, "FAILURE","You do not have the required permissions to access this resource.");
    }

    public void getCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies
        ) {
            if (cookie.getName().equals("userName")){
                userName = cookie.getValue();
            } else if (cookie.getName().equals("token")){
                token = cookie.getValue();
            }
        }
    }

    public Boolean userIsValid(){
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

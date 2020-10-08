package com.account.repository;

import com.account.models.RegisteredUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<RegisteredUser, Long>{

    List<RegisteredUser> findAll();
    List<RegisteredUser> findByRole(String role);
    RegisteredUser findById(long id);
    RegisteredUser findByIdNumber(Integer idNumber);
    RegisteredUser findByUserName(String userName);
    RegisteredUser findByIdNumberAndUserName(Integer idNumber, String userName);
    RegisteredUser findByUserNameAndToken(String userName, String token);
    RegisteredUser findByUserNameAndPassword(String userName, String password);

}


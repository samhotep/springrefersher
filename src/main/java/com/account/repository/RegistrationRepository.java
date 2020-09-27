package com.account.repository;

import com.account.models.RegisteredUser;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<RegisteredUser, Long>{

    RegisteredUser findById(long id);
    RegisteredUser findByUserName(String userName);
    RegisteredUser findByIdNumber(Integer idNumber);
    RegisteredUser findByUserNameAndPassword(String userName, String password);

}


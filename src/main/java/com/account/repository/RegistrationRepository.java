package com.account.repository;

import com.account.models.RegisteredUser;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<RegisteredUser, Long>{

    RegisteredUser findById(long id);
    RegisteredUser findByIdNumber(Integer idNumber);
    RegisteredUser findByUserName(String userName);
    RegisteredUser findByIdNumberAndUserName(Integer idNumber, String userName);
    RegisteredUser findByUserNameAndPassword(String userName, String password);

}


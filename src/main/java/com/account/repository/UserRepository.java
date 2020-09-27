package com.account.repository;

import com.account.models.User;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

//    Login function, find user, if user exists, find user and password combination
    User findByUserName(String userName);
    User findByUserNameAndPassword(String userName, String password);

//    Registration, if the user already exists in the registration table deny registration, otherwise:
//    Check the idNumber to see if it exists in the users table, deny if it doesn't, then:
    User findByIdNumber(Integer idNumber);

//    Check all the fields for verification before registering a user to the registration table

    User findByIdNumberAndFirstName(Integer idNumber, String firstName);
    User findByIdNumberAndMiddleName(Integer idNumber, String lastName);
    User findByIdNumberAndLastName(Integer idNumber, String middleName);
    User findByIdNumberAndCountry(Integer idNumber, String country);
    User findByIdNumberAndDob(Integer idNumber, Date dob);
    User findByIdNumberAndPassword(Integer idNumber, String password);

//    If these fields check out, then register the user and redirect to the login page

    User findById(long id);
}


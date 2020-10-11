package com.account.repository;

import com.account.models.Administrator;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
    Administrator findById(Integer id);
    Administrator findByUserName(String username);
    Administrator findByUserNameAndPassword(String userName, String password);
    Administrator findByUserNameAndToken(String userName, String token);
    Administrator findByRole(String role);
}

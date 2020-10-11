package com.account.repository;

import com.account.models.Administrator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
    List<Administrator> findAll();
    Administrator findById(Integer id);
    List<Administrator> findByRole(String role);
    Administrator findByUserName(String username);
    Administrator findByUserNameAndPassword(String userName, String password);
    Administrator findByUserNameAndToken(String userName, String token);
}

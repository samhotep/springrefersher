package com.account.repository;

import com.account.models.Administrator;
import com.account.models.Permissions;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
    Administrator findById(Integer id);
    Administrator findByUserName(String username);
    Administrator findByUserNameAndPassword(String username, String password);
    Administrator findByPermissions(Permissions permissions);
    Administrator findByRole(String role);
}

package com.account.repository;


import com.account.models.Administrator;
import com.account.models.Permissions;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PermissionsRepo extends CrudRepository<Permissions, Long> {

    Permissions findById( Integer id);
    Permissions findByName(String name);
    Set<Permissions> findByAdministrator(Administrator administrator);
    Permissions findByAdministratorAndName(Administrator administrator, String name);
}

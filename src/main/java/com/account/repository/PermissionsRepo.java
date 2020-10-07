package com.account.repository;


import com.account.models.Permissions;
import org.springframework.data.repository.CrudRepository;

public interface PermissionsRepo extends CrudRepository<Permissions, Long> {

    Permissions findById( Integer id);
    Permissions findByName(String name);

}

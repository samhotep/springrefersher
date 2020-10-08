package com.account.models;

import java.util.Set;

public interface User {
    Long id = null;
    String userName = null;
    String password = null;
    String token = null;
    String role = null;
    Set<Permissions> permissions = null;
}

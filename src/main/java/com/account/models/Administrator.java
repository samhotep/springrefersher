package com.account.models;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

@Entity
public class Administrator implements User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String token;
    private String role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administrator")
    private Set<Permissions> permissions;

    protected Administrator() {}

    public Administrator(String userName, String password, String role, Set<Permissions> permissions) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.permissions = permissions;
        this.token = generateToken();
    }

    private String generateToken(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 64;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}

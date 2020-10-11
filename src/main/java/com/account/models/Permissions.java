package com.account.models;

import javax.persistence.*;

@Entity
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Administrator administrator;

    public Permissions() {}

    public Permissions(String name) {
        this.name = name;
    }

    public Permissions(String name, Administrator administrator) {
        this.name = name;
        this.administrator = administrator;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

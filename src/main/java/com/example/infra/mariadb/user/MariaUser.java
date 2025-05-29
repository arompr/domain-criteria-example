package com.example.infra.mariadb.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class MariaUser {
    @Id
    @Column(name = "id")
    public UUID id;

    @Column(name = "email")
    public String email;

    @Column(name = "firstName")
    public String firstName;

    @Column(name = "name")
    public String name;

    public MariaUser() {}

    public MariaUser(UUID id, String email, String firstName, String name) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.name = name;
    }
}

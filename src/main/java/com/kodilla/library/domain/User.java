package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public final class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SIGN_UP_DATE")
    private LocalDateTime signUpDate;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.signUpDate = LocalDateTime.now();
    }
}
package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SIGN_UP_DATE")
    private LocalDate signUpDate;

    @OneToMany(targetEntity = Rent.class,
            mappedBy = "user")
    private List<Rent> rentals = new ArrayList<>();

    public User(Long id, String name, String surname, LocalDate signUpDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.signUpDate = signUpDate;
    }
}
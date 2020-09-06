package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "SIGN_UP_DATE")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private Date signUpDate;

    public User(String name, String surname, Date signUpDate) {
        this.name = name;
        this.surname = surname;
        this.signUpDate = signUpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(signUpDate, user.signUpDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, signUpDate);
    }
}
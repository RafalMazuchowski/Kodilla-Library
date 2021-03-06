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
@Entity(name = "RENTS")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENT_ID", unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "SPECIMEN_ID")
    private Specimen specimen;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "RENT_DATE")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private Date rentDate;

    @Column(name = "RETURN_DATE")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private Date returnDate;

    public Rent(Specimen specimen, User user, Date rentalDate, Date returnDate) {
        this.specimen = specimen;
        this.user = user;
        this.rentDate = rentalDate;
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Objects.equals(id, rent.id) &&
                Objects.equals(specimen, rent.specimen) &&
                Objects.equals(user, rent.user) &&
                Objects.equals(rentDate, rent.rentDate) &&
                Objects.equals(returnDate, rent.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specimen, user, rentDate, returnDate);
    }
}
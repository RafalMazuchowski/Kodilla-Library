package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RENTS")
public final class Rent {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long rentId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "SPECIMEN_ID")
    private long specimenId;

    @Column(name = "RENT_DATE")
    private LocalDateTime rentDate;

    @Column(name = "RETURN_DATE")
    private LocalDateTime returnDate;

    public Rent(long userId, long specimenId, LocalDateTime rentDate, LocalDateTime returnDate) {
        this.userId = userId;
        this.specimenId = specimenId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}
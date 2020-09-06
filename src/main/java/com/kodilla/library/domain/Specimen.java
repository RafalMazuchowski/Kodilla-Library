package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "SPECIMENS")
public class Specimen {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(targetEntity = Rent.class,
            mappedBy = "rents")
    private List<Rent> rentals = new ArrayList<>();

    public Specimen(Long id, Book book, String status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }

}
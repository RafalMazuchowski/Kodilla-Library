package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "SPECIMENS")
public class Specimen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPECIMEN_ID", unique = true)
    private Long id;

    @Column(name = "STATUS")
    private SpecimenStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Specimen(SpecimenStatus status, Book book) {
        this.status = status;
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specimen specimen = (Specimen) o;
        return Objects.equals(id, specimen.id) &&
                status == specimen.status &&
                Objects.equals(book, specimen.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, book);
    }

}
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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SPECIMENS")
public final class Specimen {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "BOOK_ID")
    private long bookId;

    @Column(name = "STATUS")
    private String status;

    public Specimen(long bookId) {
        this.bookId = bookId;
        this.status = String.format("Book recently added to library (on %s)", LocalDateTime.now().toString());
    }
}
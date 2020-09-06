package com.kodilla.library.dao;

import com.kodilla.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    @Override
    Book save(Book book);

    @Override
    List<Book> findAll();

    Optional<Book> findById(Long id);
}
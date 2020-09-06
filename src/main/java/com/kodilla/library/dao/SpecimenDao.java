package com.kodilla.library.dao;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.SpecimenStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecimenDao extends CrudRepository<Specimen, Long> {
    @Override
    Specimen save(Specimen specimen);

    @Override
    List<Specimen> findAll();

    List<Specimen> findAllByStatusAndTitle(SpecimenStatus status, Book book);

    Optional<Specimen> findById(Long id);
}
package com.kodilla.library.dao;

import com.kodilla.library.domain.Specimen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecimenDao extends CrudRepository<Specimen, Long> {
    List<Specimen> findSpecimenByBookId(long bookId);
}
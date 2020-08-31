package com.kodilla.library.dao;

import com.kodilla.library.domain.Specimen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecimenDao extends CrudRepository<Specimen, Long> {
    static List<Specimen> findSpecimensByBookId(long bookId) {
        return null;
    }
}
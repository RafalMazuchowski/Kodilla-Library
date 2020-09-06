package com.kodilla.library.dao;

import com.kodilla.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentDao extends CrudRepository<Rent, Long> {
    @Override
    Rent save(Rent rent);

    @Override
    List<Rent> findAll();

    Optional<Rent> findById(Long id);
}
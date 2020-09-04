package com.kodilla.library.dao;

import com.kodilla.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDao extends CrudRepository<Rent, Long> {
}
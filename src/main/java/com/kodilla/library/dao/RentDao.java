package com.kodilla.library.dao;

import com.kodilla.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentDao extends CrudRepository<Rent, Long> {
}
package com.kodilla.library.dao;

import com.kodilla.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    @Override
    User save(User user);

    @Override
    List<User> findAll();

    Optional<User> findById(Long id);
}
package com.kodilla.library.dao;

import com.kodilla.library.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long>{
}
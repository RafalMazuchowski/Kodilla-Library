package com.kodilla.library.domain;

import com.kodilla.library.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfUsers = userDao.findAll().size();
        User user1 = new User("Test1", "User", Date.valueOf(LocalDate.now()));
        User user2 = new User("Test2", "User", Date.valueOf("1999-09-23"));
        User user3 = new User("Test3", "User", Date.valueOf("2002-01-02"));

        //When
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        List<User> users = userDao.findAll();
        int numberOfUsers = users.size();

        //Then
        assertEquals(initialNumberOfUsers + 3, numberOfUsers);
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertTrue(users.contains(user3));

        //CleanUp
        userDao.delete(user1);
        userDao.delete(user2);
        userDao.delete(user3);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        User user = new User("Test", "User", Date.valueOf(LocalDate.now()));
        userDao.save(user);

        //When
        User foundByIdUser = userDao.findById(user.getId()).get();

        //Then
        assertEquals(user, foundByIdUser);

        //CleanUp
        userDao.delete(user);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfUsers = userDao.findAll().size();
        User user = new User("Test", "User", Date.valueOf(LocalDate.now()));
        userDao.save(user);

        //When
        userDao.delete(user);
        List<User> users = userDao.findAll();
        int numberOfUsers = users.size();

        //Then
        assertEquals(initialNumberOfUsers, numberOfUsers);
        assertFalse(users.contains(user));
    }
}
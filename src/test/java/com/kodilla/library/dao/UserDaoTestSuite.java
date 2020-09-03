package com.kodilla.library.dao;

import com.kodilla.library.domain.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTestSuite {

    @Autowired
    private UserDao userDao;

    @Test
    public void testUserDaoSave() {

        // Given
        User newUser = new User("User", "Kodilla");

        // When
        userDao.save(newUser);
        long id = newUser.getId();
        User fetchedUser = userDao.findById(id).get();

        // Then
        Assert.assertThat(fetchedUser.getId(), Matchers.equalTo(id));
        userDao.delete(fetchedUser);
    }

}
package com.kodilla.library.dao;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentDaoTestSuite {

    @Autowired
    RentDao rentDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    SpecimenDao specimenDao;
    @Autowired
    UserDao userDao;

    @Test
    public void testBookRent() {

        // Given
        User newUser = new User("User", "Kodilla");
        userDao.save(newUser);

        Book testBook = new Book("Test", "Kodilla Course", 2020);
        bookDao.save(testBook);

        Specimen testSpecimen = new Specimen(testBook.getId());
        specimenDao.save(testSpecimen);

        // When
        Rent rentTestBook = new Rent(newUser.getId(), testSpecimen.getId(), LocalDateTime.now(), null);
        rentDao.save(rentTestBook);

        Rent fetchedRent = rentDao.findById(rentTestBook.getRentId()).get();

        // Then
        Assert.assertThat(rentTestBook.getRentId(), Matchers.equalTo(fetchedRent.getRentId()));


        // Clean-up
        rentDao.delete(rentTestBook);
        specimenDao.delete(testSpecimen);
        bookDao.delete(testBook);
        userDao.delete(newUser);
    }

    @Test
    public void testBookReturn() {
        // Given
        User newUser = new User("User", "Kodilla");
        userDao.save(newUser);

        Book testBook = new Book("Test", "Kodilla Course", 2020);
        bookDao.save(testBook);

        Specimen testSpecimen = new Specimen(testBook.getId());
        specimenDao.save(testSpecimen);

        Rent rentTestBook = new Rent(newUser.getId(), testSpecimen.getId(), LocalDateTime.of(2020, 9, 3, 21, 12, 15), null);
        rentDao.save(rentTestBook);

        Rent fetchedRent = rentDao.findById(rentTestBook.getRentId()).get();
        fetchedRent.setReturnDate(LocalDateTime.of(2020, 9, 17, 21, 12, 15));
        rentDao.save(fetchedRent);

        // When
        Rent checkRentReturn = rentDao.findById(rentTestBook.getRentId()).get();

        // Then
        Assert.assertThat(fetchedRent.getReturnDate(), Matchers.equalTo(checkRentReturn.getReturnDate()));

        // Clean-up
        rentDao.delete(rentTestBook);
        specimenDao.delete(testSpecimen);
        bookDao.delete(testBook);
        userDao.delete(newUser);
    }
}

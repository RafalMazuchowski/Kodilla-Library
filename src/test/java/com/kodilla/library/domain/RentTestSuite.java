package com.kodilla.library.domain;

import com.kodilla.library.dao.BookDao;
import com.kodilla.library.dao.RentDao;
import com.kodilla.library.dao.SpecimenDao;
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
public class RentTestSuite {
    @Autowired
    private RentDao rentDao;
    @Autowired
    private SpecimenDao specimenDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfRents = rentDao.findAll().size();
        Book book = new Book("It", "Stephen King", 2001);
        Specimen specimen = new Specimen(SpecimenStatus.AVAILABLE, book);
        User user = new User("Test1", "User", Date.valueOf(LocalDate.now()));
        bookDao.save(book);
        specimenDao.save(specimen);
        userDao.save(user);
        Rent rent1 = new Rent(
                specimen,
                user,
                Date.valueOf("2020-08-09"),
                Date.valueOf("2020-09-08")
        );
        Rent rent2 = new Rent(
                specimen,
                user,
                Date.valueOf("2001-02-29"),
                Date.valueOf("2011-12-24")
        );

        //When
        rentDao.save(rent1);
        rentDao.save(rent2);
        List<Rent> rentList = rentDao.findAll();
        int numberOfRents = rentList.size();

        //Then
        assertEquals(initialNumberOfRents + 2, numberOfRents);
        assertTrue(rentList.contains(rent1));
        assertTrue(rentList.contains(rent2));

        //CleanUp
        rentDao.delete(rent1);
        rentDao.delete(rent2);
        specimenDao.delete(specimen);
        bookDao.delete(book);
        userDao.delete(user);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Book book = new Book("It", "Stephen King", 2001);
        Specimen specimen = new Specimen(SpecimenStatus.AVAILABLE, book);
        User user = new User("Test1", "User", Date.valueOf(LocalDate.now()));
        bookDao.save(book);
        specimenDao.save(specimen);
        userDao.save(user);
        Rent rent = new Rent(
                specimen,
                user,
                Date.valueOf("2020-08-09"),
                Date.valueOf("2020-09-08")
        );
        rentDao.save(rent);

        //When
        Rent foundRent = rentDao.findById(rent.getId()).get();

        //Then
        assertEquals(rent, foundRent);

        //CleanUp
        rentDao.delete(rent);
        userDao.delete(user);
        specimenDao.delete(specimen);
        bookDao.delete(book);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfRents = rentDao.findAll().size();
        Book book = new Book("It", "Stephen King", 2001);
        Specimen specimen = new Specimen(SpecimenStatus.AVAILABLE, book);
        User user = new User("Test1", "User", Date.valueOf(LocalDate.now()));
        bookDao.save(book);
        specimenDao.save(specimen);
        userDao.save(user);
        Rent rent = new Rent(
                specimen,
                user,
                Date.valueOf("2020-08-09"),
                Date.valueOf("2020-09-08")
        );
        rentDao.save(rent);

        //When
        rentDao.delete(rent);
        List<Rent> rentList = rentDao.findAll();
        int numberOfRents = rentList.size();

        //Then
        assertEquals(initialNumberOfRents, numberOfRents);
        assertFalse(rentList.contains(rent));

        //CleanUp
        userDao.delete(user);
        specimenDao.delete(specimen);
        bookDao.delete(book);
    }
}

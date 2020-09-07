package com.kodilla.library.service;

import com.kodilla.library.dao.BookDao;
import com.kodilla.library.dao.RentDao;
import com.kodilla.library.dao.SpecimenDao;
import com.kodilla.library.dao.UserDao;
import com.kodilla.library.domain.*;
import com.kodilla.library.domain.dto.SpecimenDto;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.exception.RentNotFoundException;
import com.kodilla.library.exception.SpecimenNotFoundException;
import com.kodilla.library.exception.WrongSpecimenStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DbService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private SpecimenDao specimenDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RentDao rentDao;

    //Book service
    public Book addBook(Book book) {
        return bookDao.save(book);
    }

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    //User service
    public User addUser(User user) {
        return userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    //Specimen service
    public Specimen addSpecimen(Specimen specimen) {
        return specimenDao.save(specimen);
    }

///////////////////////////////////////////////////////
    public int numberOfAvailableSpecimens(Long bookId) {
        return specimenDao.findAllByStatusAndTitle(SpecimenStatus.AVAILABLE,
                bookDao.findById(bookId).orElseThrow(BookNotFoundException::new)).size();
    }

    public Specimen updateSpecimenStatus(SpecimenDto updatedSpecimen, long id) {
        Specimen specimen = specimenDao.findById(id).orElseThrow(SpecimenNotFoundException::new);
        if (updatedSpecimen.getStatus() != null) {
            specimen.setStatus(updatedSpecimen.getStatus());
        }
        return specimenDao.save(specimen);
    }

    //Rent service
    public Rent addRent(Rent rent) {
        Specimen specimen = specimenDao.findById(rent.getSpecimen().getId()).orElseThrow(SpecimenNotFoundException::new);

        if (specimen.getStatus() == SpecimenStatus.AVAILABLE) {
            specimen.setStatus(SpecimenStatus.RENTED);
        } else throw new WrongSpecimenStatusException();

        specimenDao.save(specimen);
        return rentDao.save(rent);
    }

    public Rent returnBook(Long rentId) {
        Rent rent = rentDao.findById(rentId).orElseThrow(RentNotFoundException::new);
        Specimen specimen = rent.getSpecimen();
        if (specimen.getStatus() == SpecimenStatus.RENTED) {
            specimen.setStatus(SpecimenStatus.AVAILABLE);
        } else throw new WrongSpecimenStatusException();

        specimenDao.save(specimen);
        rent.setReturnDate(Date.valueOf(LocalDate.now()));
        return rentDao.save(rent);
    }
}

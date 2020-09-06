package com.kodilla.library.service;

import com.kodilla.library.dao.BookDao;
import com.kodilla.library.dao.RentDao;
import com.kodilla.library.dao.SpecimenDao;
import com.kodilla.library.dao.UserDao;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class DbService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private SpecimenDao specimenDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RentDao rentDao;

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Specimen getSpecimen(Long specimenId) throws RuntimeException {
        return specimenDao.findById(specimenId).orElseThrow(RuntimeException::new);
    }

    public User getUser(Long userId) throws RuntimeException {
        return userDao.findById(userId).orElseThrow(RuntimeException::new);
    }

    public Rent rent(Specimen specimen, User user) {
        Rent rent = new Rent(null, specimen, user, LocalDate.now(), null);
        rentDao.save(rent);
        return rent;
    }

    public Book getBook(Long bookId) throws RuntimeException {
        return bookDao.findById(bookId).orElseThrow(RuntimeException::new);
    }

    public Rent returnBook(Rent rent) {
        rent.setReturnDate(LocalDate.now());
        rentDao.save(rent);
        return rent;
    }

    public User saveUser(final User user) {
        return userDao.save(user);
    }

    public Book saveBook(Book book) {
        return bookDao.save(book);
    }

    public Specimen saveSpecimen(Specimen specimen) {
        specimenDao.save(specimen);
        return specimen;
    }
}

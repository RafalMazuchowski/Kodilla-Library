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
import java.util.Optional;

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

    public Specimen getSpecimen(Long specimenId) throws Exception{
        return specimenDao.findById(specimenId).orElseThrow(Exception::new);
    }

    public User getUser(Long userId) throws Exception{
        return userDao.findById(userId).orElseThrow(Exception::new);
    }

    public void rent(Specimen specimen, User user) {
        Rent rent = new Rent(null, specimen, user, LocalDate.now(), null);
        rentDao.save(rent);
    }

    public Book getBook(Long bookId) throws Exception{
        return bookDao.findById(bookId).orElseThrow(Exception::new);
    }

    public void returnBook(Rent rent) {
        rent.setReturnDate(LocalDate.now());
        rentDao.save(rent);
    }

    public User saveUser(final User user) {
        return userDao.save(user);
    }

    public Book saveBook(Book book) {
        return bookDao.save(book);
    }

    public void saveSpecimen(Specimen specimen) {
        specimenDao.save(specimen);
    }
}

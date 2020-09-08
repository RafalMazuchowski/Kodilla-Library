package com.kodilla.library.domain;

import com.kodilla.library.dao.BookDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTestSuite {
    @Autowired
    private BookDao bookDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfBooks = bookDao.findAll().size();
        Book book1 = new Book("It", "Stephen King", 2001);
        Book book2 = new Book("Needful Things", "Stephen King", 1993);

        //When
        bookDao.save(book1);
        bookDao.save(book2);
        List<Book> books = bookDao.findAll();
        int numberOfBooks = books.size();

        //Then
        assertEquals(initialNumberOfBooks + 2, numberOfBooks);
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));

        //CleanUp
        bookDao.delete(book1);
        bookDao.delete(book2);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Book book = new Book("It", "Stephen King", 2001);
        bookDao.save(book);

        //When
        Book foundBook = bookDao.findById(book.getId()).get();

        //Then
        assertEquals(book, foundBook);

        //CleanUp
        bookDao.delete(book);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfBooks = bookDao.findAll().size();
        Book book = new Book("It", "Stephen King", 2001);
        bookDao.save(book);

        //When
        bookDao.delete(book);
        List<Book> books = bookDao.findAll();
        int numberOfBooks = books.size();

        //Then
        assertEquals(initialNumberOfBooks, numberOfBooks);
        assertFalse(books.contains(book));
    }
}

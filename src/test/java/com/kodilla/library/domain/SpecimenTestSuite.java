package com.kodilla.library.domain;

import com.kodilla.library.dao.BookDao;
import com.kodilla.library.dao.SpecimenDao;
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
public class SpecimenTestSuite {

    @Autowired
    private SpecimenDao specimenDao;
    @Autowired
    private BookDao bookDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        Book book1 = new Book("It", "Stephen King", 2001);
        Book book2 = new Book("Needful Things", "Stephen King", 1993);
        bookDao.save(book1);
        bookDao.save(book2);

        int initialNumberOfSpecimens = specimenDao.findAll().size();
        Specimen specimen1 = new Specimen(SpecimenStatus.AVAILABLE, book1);
        Specimen specimen2 = new Specimen(SpecimenStatus.LOST, book1);
        Specimen specimen3 = new Specimen(SpecimenStatus.DESTROYED, book2);

        //When
        specimenDao.save(specimen1);
        specimenDao.save(specimen2);
        specimenDao.save(specimen3);
        List<Specimen> specimenList = specimenDao.findAll();
        int numberOfSpecimens = specimenList.size();

        //Then
        assertEquals(initialNumberOfSpecimens + 3, numberOfSpecimens);
        assertTrue(specimenList.contains(specimen1));
        assertTrue(specimenList.contains(specimen2));
        assertTrue(specimenList.contains(specimen3));

        //CleanUp
        specimenDao.delete(specimen1);
        specimenDao.delete(specimen2);
        specimenDao.delete(specimen3);
        bookDao.delete(book1);
        bookDao.delete(book2);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Book book = new Book("It", "Stephen King", 2001);
        bookDao.save(book);

        Specimen specimen = new Specimen(SpecimenStatus.AVAILABLE, book);
        specimenDao.save(specimen);

        //When
        Specimen foundSpecimen = specimenDao.findById(specimen.getId()).get();

        //Then
        assertEquals(specimen, foundSpecimen);

        //CleanUp
        specimenDao.delete(specimen);
        bookDao.delete(book);
    }

    @Transactional
    @Test
    public void testFindByStatusAnsBook() {
        //Given
        Book book1 = new Book("It", "Stephen King", 2001);
        Book book2 = new Book("Needful Things", "Stephen King", 1993);
        bookDao.save(book1);
        bookDao.save(book2);

        Specimen specimen1 = new Specimen(SpecimenStatus.AVAILABLE, book1);
        Specimen specimen2 = new Specimen(SpecimenStatus.AVAILABLE, book1);
        Specimen specimen3 = new Specimen(SpecimenStatus.DESTROYED, book1);
        Specimen specimen4 = new Specimen(SpecimenStatus.LOST, book2);
        specimenDao.save(specimen1);
        specimenDao.save(specimen2);
        specimenDao.save(specimen3);
        specimenDao.save(specimen4);

        //When
        List<Specimen> books1 = specimenDao.findAllByBookAndStatus(book1, SpecimenStatus.AVAILABLE);
        List<Specimen> books2 = specimenDao.findAllByBookAndStatus(book1, SpecimenStatus.DESTROYED);
        List<Specimen> books3 = specimenDao.findAllByBookAndStatus(book2, SpecimenStatus.LOST);
        List<Specimen> books4 = specimenDao.findAllByBookAndStatus(book2, SpecimenStatus.AVAILABLE);

        //Then
        assertTrue(books1.contains(specimen1));
        assertTrue(books1.contains(specimen2));
        assertTrue(books2.contains(specimen3));
        assertTrue(books3.contains(specimen4));
        assertFalse(books1.contains(specimen4));
        assertFalse(books4.contains(specimen2));

        //CleanUp
        specimenDao.delete(specimen1);
        specimenDao.delete(specimen2);
        specimenDao.delete(specimen3);
        specimenDao.delete(specimen4);
        bookDao.delete(book1);
        bookDao.delete(book2);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfSpecimens = specimenDao.findAll().size();
        Book book = new Book("It", "Stephen King", 2001);
        bookDao.save(book);
        Specimen specimen = new Specimen(SpecimenStatus.AVAILABLE, book);
        specimenDao.save(specimen);

        //When
        specimenDao.delete(specimen);
        List<Specimen> specimenList = specimenDao.findAll();
        int numberOfSpecimens = specimenList.size();

        //Then
        assertEquals(initialNumberOfSpecimens, numberOfSpecimens);
        assertFalse(specimenList.contains(specimen));

        //CleanUp
        bookDao.delete(book);
    }
}
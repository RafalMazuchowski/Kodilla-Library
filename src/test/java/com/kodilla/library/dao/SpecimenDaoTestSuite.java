package com.kodilla.library.dao;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Specimen;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecimenDaoTestSuite {

    @Autowired
    private SpecimenDao specimenDao;
    @Autowired
    private BookDao bookDao;

    @Test
    public void testSaveSpecimen() {

        // Given
        Book testBook = new Book("Test", "test", 2020);
        bookDao.save(testBook);

        Specimen newSpecimen = new Specimen(testBook.getId());

        // When
        specimenDao.save(newSpecimen);
        long id = newSpecimen.getId();
        Specimen fetchedSpecimen = specimenDao.findById(id).get();

        // Then
        Assert.assertThat(fetchedSpecimen.getId(), Matchers.equalTo(id));

        // Clean-up
        specimenDao.delete(newSpecimen);
        bookDao.delete(testBook);
    }

    @Test
    public void testModifySpecimenStatus() {

        // Given
        Book testBook = new Book("Modify", "test_modify", 2020);
        bookDao.save(testBook);

        Specimen newSpecimen = new Specimen(testBook.getId());
        specimenDao.save(newSpecimen);

        // When
        Specimen fetchedSpecimen = specimenDao.findById(newSpecimen.getId()).get();
        fetchedSpecimen.setStatus("Sorry man... Too late!");
        specimenDao.save(fetchedSpecimen);

        // Then
        Assert.assertThat(fetchedSpecimen.getStatus(), Matchers.equalTo(specimenDao.findById(newSpecimen.getId()).get().getStatus()));

        // Clean-up
        specimenDao.delete(newSpecimen);
        bookDao.delete(testBook);
    }

    @Test
    public void testFetchNumberOfBookSpecimens(){

        // Given
        Book testBook = new Book("Numbers", "test_Of_numbers", 2020);
        bookDao.save(testBook);

        Specimen newSpecimen1 = new Specimen(testBook.getId());
        Specimen newSpecimen2 = new Specimen(testBook.getId());
        specimenDao.save(newSpecimen1);
        specimenDao.save(newSpecimen2);

        // When
        List<Specimen> foundSpecimen = specimenDao.findSpecimenByBookId(testBook.getId());

        // Then
        Assert.assertThat(foundSpecimen.size(), Matchers.equalTo(2));

        // Clean-up
        specimenDao.delete(newSpecimen1);
        specimenDao.delete(newSpecimen2);
        bookDao.delete(testBook);
    }
}

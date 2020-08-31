package com.kodilla.library.controller;

import com.kodilla.library.mapper.LibraryMapper;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.dao.*;
import com.kodilla.library.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("kodilla/library")
public class LibraryController {
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    SpecimenDao specimenDao;
    @Autowired
    RentDao rentDao;
    @Autowired
    LibraryMapper libraryMapper;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        userDao.save(libraryMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookDao.save(libraryMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addSpecimen", consumes = APPLICATION_JSON_VALUE)
    public void addSpecimen(@RequestBody SpecimenDto specimenDto) {
        specimenDao.save(libraryMapper.mapToSpecimen(specimenDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateSpecimen", consumes = APPLICATION_JSON_VALUE)
    public SpecimenDto updateSpecimen(@RequestBody SpecimenDto specimenDto) {
        Specimen fetchedSpecimen = specimenDao.findById(specimenDto.getId()).get();
        fetchedSpecimen.setStatus(specimenDto.getStatus());
        return libraryMapper.mapToSpecimenDto(specimenDao.save(fetchedSpecimen));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getSpecimens")
    public List<SpecimenDto> getSpecimens(@RequestParam long bookId) {
        return libraryMapper.mapToSpecimensList(SpecimenDao.findSpecimensByBookId(bookId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentSpecimen")
    public void rentSpecimen(@RequestParam long userId, long specimenId) {
        rentDao.save(new Rent(userId,specimenId, LocalDateTime.now(), null));
    }

    @RequestMapping(method = RequestMethod.POST, value = "returnSpecimen")
    public RentDto returnSpecimen(@RequestParam long specimenId) {
        Rent fetchedRent = rentDao.findById(specimenId).get();
        fetchedRent.setReturnDate(LocalDateTime.now());
        return libraryMapper.mapToRentalDto(rentDao.save(fetchedRent));
    }
}
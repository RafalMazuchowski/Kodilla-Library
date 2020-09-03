package com.kodilla.library.controller;

import com.kodilla.library.dao.BookDao;
import com.kodilla.library.dao.RentDao;
import com.kodilla.library.dao.SpecimenDao;
import com.kodilla.library.dao.UserDao;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.BookDto;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.dto.SpecimenDto;
import com.kodilla.library.dto.UserDto;
import com.kodilla.library.mapper.LibraryMapper;
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

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        return libraryMapper.mapToUsersList((List<User>) userDao.findAll());
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
        return libraryMapper.mapToSpecimensList(specimenDao.findSpecimenByBookId(bookId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentSpecimen")
    public void rentSpecimen(@RequestParam long userId, long specimenId) {
        rentDao.save(new Rent(userId, specimenId, LocalDateTime.now(), null));
    }

    @RequestMapping(method = RequestMethod.POST, value = "returnSpecimen")
    public RentDto returnSpecimen(@RequestParam long specimenId) {
        Rent fetchedRent = rentDao.findById(specimenId).get();
        fetchedRent.setReturnDate(LocalDateTime.now());
        return libraryMapper.mapToRentDto(rentDao.save(fetchedRent));
    }
}

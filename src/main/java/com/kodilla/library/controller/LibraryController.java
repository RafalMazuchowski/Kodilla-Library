package com.kodilla.library.controller;

import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.domain.dto.RentDto;
import com.kodilla.library.domain.dto.SpecimenDto;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.mapper.LibraryMapper;
import com.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("kodilla/library")
public class LibraryController {
    @Autowired
    private LibraryMapper libraryMapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "getBookList", consumes = APPLICATION_JSON_VALUE)
    public List<BookDto> getBookList() {
        return libraryMapper.mapToBookDtoList(dbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return libraryMapper.mapToBookDto(dbService.addBook(libraryMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public UserDto addBook(@RequestBody UserDto userDto) {
        return libraryMapper.mapToUserDto(dbService.addUser(libraryMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addSpecimen", consumes = APPLICATION_JSON_VALUE)
    public SpecimenDto addSpecimen(@RequestBody SpecimenDto specimenDto) {
        return libraryMapper.mapToSpecimenDto(dbService.addSpecimen(libraryMapper.mapToSpecimen(specimenDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateSpecimenStatus/{id}", consumes = APPLICATION_JSON_VALUE)
    public SpecimenDto updateSpecimenStatus(@RequestBody SpecimenDto specimenDto, @PathVariable("id") Long specimenId) {
        return libraryMapper.mapToSpecimenDto(dbService.updateSpecimenStatus(specimenDto, specimenId));
    }
////////////////////////////////////////
    @RequestMapping(method = RequestMethod.GET, value = "numberOfAvailableSpecimens/{bookId}", consumes = APPLICATION_JSON_VALUE)
    public int numberOfSpecimens(@PathVariable("bookId") Long bookId) {
        return dbService.numberOfAvailableSpecimens(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentBook", consumes = APPLICATION_JSON_VALUE)
    public RentDto addRent(@RequestBody RentDto rentDto) {
        return libraryMapper.mapToRentDto(dbService.addRent(libraryMapper.mapToRent(rentDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook/{rentId}", consumes = APPLICATION_JSON_VALUE)
    public RentDto returnBook(@PathVariable("rentId") long rentId) {
        return libraryMapper.mapToRentDto(dbService.returnBook(rentId));
    }
}

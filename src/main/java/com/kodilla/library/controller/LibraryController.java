package com.kodilla.library.controller;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.BookDto;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.dto.SpecimenDto;
import com.kodilla.library.dto.UserDto;
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
    LibraryMapper libraryMapper;
    @Autowired
    private DbService dbService;

    @RequestMapping(method = RequestMethod.GET, value = "getBookList")
    public List<BookDto> getBookList() {
        return libraryMapper.mapToBookDtoList(dbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody UserDto userDto) {
        return dbService.saveUser(libraryMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = APPLICATION_JSON_VALUE)
    public Book createBook(@RequestBody BookDto bookDto) {
        return dbService.saveBook(libraryMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createSpecimen", consumes = APPLICATION_JSON_VALUE)
    public Specimen createSpecimen(@RequestBody SpecimenDto specimenDto) throws RuntimeException {
        return dbService.saveSpecimen(libraryMapper.mapToSpecimen(specimenDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateSpecimenStatus")
    public SpecimenDto updateSpecimenStatus(@RequestBody SpecimenDto specimenDto) {
        return new SpecimenDto(1L, 2L, "Updated status");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getSpecimenQuantity")
    public SpecimenDto getSpecimenQuantity(@RequestParam Long bookId) {
        return new SpecimenDto(1L, 2L, "Status");
    }

    @RequestMapping(method = RequestMethod.POST, value = "rent")
    public Rent rent(@RequestParam Long specimenId, @RequestParam Long userId) throws RuntimeException {
        return dbService.rent(libraryMapper.mapToSpecimen(specimenId), libraryMapper.mapToUser(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "returnBook")
    public Rent returnBook(@RequestBody RentDto rentDto) throws RuntimeException {
        return dbService.returnBook(libraryMapper.mapToRent(rentDto));
    }
}

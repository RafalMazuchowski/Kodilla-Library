package com.kodilla.library.controller;

import com.kodilla.library.dto.BookDto;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.dto.SpecimenDto;
import com.kodilla.library.dto.UserDto;
import com.kodilla.library.mapper.LibraryMapper;
import com.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("kodilla/library")
public class LibraryController {
    @Autowired
    private DbService dbService;
    @Autowired
    LibraryMapper libraryMapper;


    @RequestMapping(method = RequestMethod.GET, value = "getBookList")
    public List<BookDto> getBookList() {
        return libraryMapper.mapToBookDtoList(dbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        dbService.saveUser(libraryMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        dbService.saveBook(libraryMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createSpecimen", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createSpecimen(@RequestBody SpecimenDto specimenDto) throws Exception {
        dbService.saveSpecimen(libraryMapper.mapToSpecimen(specimenDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateSpecimenStatus")
    public SpecimenDto updateSpecimenStatus (@RequestBody SpecimenDto specimenDto) {
        return new SpecimenDto(1L, 2L, "Updated status");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getSpecimenQuantity")
    public SpecimenDto getSpecimenQuantity (@RequestParam Long bookId) {
        return new SpecimenDto(1L, 2L, "Status");
    }

    @RequestMapping(method = RequestMethod.POST, value = "rent")
    public void rent(@RequestParam Long specimenId, @RequestParam Long userId) throws Exception {
        dbService.rent(libraryMapper.mapToSpecimen(specimenId), libraryMapper.mapToUser(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "returnBook")
    public void returnBook(@RequestBody RentDto rentDto) throws Exception {
        dbService.returnBook(libraryMapper.mapToRent(rentDto));
    }
}

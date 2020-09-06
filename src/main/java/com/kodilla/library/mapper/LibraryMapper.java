package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.BookDto;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.dto.SpecimenDto;
import com.kodilla.library.dto.UserDto;
import com.kodilla.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {
    @Autowired
    private DbService dbService;

    public Specimen mapToSpecimen(final SpecimenDto specimenDto) throws Exception {
        return new Specimen(specimenDto.getId(),
                dbService.getBook(specimenDto.getBookId()),
                specimenDto.getStatus());
    }

    public SpecimenDto mapToSpecimenDto(final Specimen specimen) {
        return new SpecimenDto(specimen.getId(),
                specimen.getBook().getId(),
                specimen.getStatus());
    }

    public List<SpecimenDto> mapToSpecimenDtoList(final List<Specimen> specimenList) {
        return specimenList.stream()
                .map(s -> new SpecimenDto(s.getId(), s.getBook().getId(), s.getStatus()))
                .collect(Collectors.toList());
    }

    public Rent mapToRent(final RentDto rentDto) throws Exception {
        return new Rent(rentDto.getId(),
                dbService.getSpecimen(rentDto.getSpecimenId()),
                dbService.getUser(rentDto.getUserId()),
                LocalDate.now(), null);
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto();
    }

    public List<RentDto> mapToRentDtoList(final List<Rent> rentsList) {
        return rentsList.stream()
                .map(r -> new RentDto(r.getId(), r.getSpecimen().getId(), r.getUser().getId(), r.getRentDate(), r.getReturnDate()))
                .collect(Collectors.toList());
    }

    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getSignUpDate());
    }

    public UserDto mapToUserDto(final User reader) {
        return new UserDto(reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getSignUpDate());
    }

    public List<UserDto> mapToReaderDtoList(final List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getSurname(), u.getSignUpDate()))
                .collect(Collectors.toList());
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear());
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear());
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(b -> new BookDto(b.getId(), b.getTitle(), b.getAuthor(), b.getPublicationYear()))
                .collect(Collectors.toList());
    }

    public Specimen mapToSpecimen(Long specimenId) throws Exception {
        return dbService.getSpecimen(specimenId);
    }

    public User mapToUser(Long userId) throws Exception {
        return dbService.getUser(userId);
    }
}
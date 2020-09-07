package com.kodilla.library.mapper;

import com.kodilla.library.dao.BookDao;
import com.kodilla.library.dao.SpecimenDao;
import com.kodilla.library.dao.UserDao;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.domain.dto.RentDto;
import com.kodilla.library.domain.dto.SpecimenDto;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.exception.SpecimenNotFoundException;
import com.kodilla.library.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SpecimenDao specimenDao;

    //Book mapping
    public Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.getTitleSome(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear());
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto(book.getTitleMore(),
                book.getAuthor(),
                book.getPublicationYear());
    }

    public List<BookDto> mapToBookDtoList(List<Book> bookList) {
        return bookList.stream()
                .map(b -> new BookDto(b.getTitleMore(), b.getAuthor(), b.getPublicationYear()))
                .collect(Collectors.toList());
    }

    //User mapping
    public User mapToUser(UserDto userDto) {
        return new User(userDto.getName(),
                userDto.getSurname(),
                userDto.getSignUpDate());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getName(),
                user.getSurname(),
                user.getSignUpDate());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(u.getName(), u.getSurname(), u.getSignUpDate()))
                .collect(Collectors.toList());
    }

    //Specimen mapping
    public Specimen mapToSpecimen(SpecimenDto specimenDto) {
        return new Specimen(specimenDto.getStatus(),
                bookDao.findById(specimenDto.getBookId())
                        .orElseThrow(BookNotFoundException::new));
    }

    public SpecimenDto mapToSpecimenDto(Specimen specimen) {
        return new SpecimenDto(specimen.getStatus(), specimen.getBook().getId());
    }

//    public List<SpecimenDto> mapToSpecimenDtoList(List<Specimen> specimenList) {
//        return specimenList.stream()
//                .map(s -> new SpecimenDto(s.getStatus(), s.getBook().getId()))
//                .collect(Collectors.toList());
//    }

    //Rent mapping
    public Rent mapToRent(RentDto rentDto) {
        return new Rent(specimenDao.findById(rentDto.getSpecimenId()).orElseThrow(SpecimenNotFoundException::new),
                userDao.findById(rentDto.getUserId()).orElseThrow(UserNotFoundException::new),
                rentDto.getRentDate(),
                rentDto.getReturnDate());
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(rent.getSpecimen().getId(), rent.getUser().getId(), rent.getRentDate(), rent.getReturnDate());
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rentsList) {
        return rentsList.stream()
                .map(r -> new RentDto(r.getSpecimen().getId(), r.getUser().getId(), r.getRentDate(), r.getReturnDate()))
                .collect(Collectors.toList());
    }
}
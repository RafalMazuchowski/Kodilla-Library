package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Specimen;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.BookDto;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.dto.SpecimenDto;
import com.kodilla.library.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getName(), userDto.getSurname());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getName(), user.getSurname(), user.getSignUpDate());
    }

    public List<UserDto> mapToUsersList(final List<User> usersList) {
        return usersList.stream().map(e -> new UserDto(e.getName(), e.getSurname(), e.getSignUpDate())).collect(Collectors.toList());
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPublicationYear());
    }

    public Specimen mapToSpecimen(final SpecimenDto specimenDto) {
        return new Specimen(specimenDto.getBookId());
    }

    public SpecimenDto mapToSpecimenDto(final Specimen specimen) {
        return new SpecimenDto(specimen.getId(), specimen.getBookId(), specimen.getStatus());
    }

    public List<SpecimenDto> mapToSpecimensList(final List<Specimen> specimenList) {
        return specimenList.stream().map(e -> new SpecimenDto(e.getId(), e.getBookId(), e.getStatus())).collect(Collectors.toList());
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(rent.getUserId(), rent.getSpecimenId(), rent.getRentDate(), rent.getReturnDate());
    }
}

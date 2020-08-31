package com.kodilla.library.mapper;

import com.kodilla.library.domain.*;
import com.kodilla.library.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {

    public Book mapToBook(final BookDto bookDto) {
        return new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPublicationYear());
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public RentDto mapToRentalDto(final Rent rent) {
        return new RentDto(rent.getUserId(), rent.getSpecimenId(), rent.getRentDate(), rent.getReturnDate());
    }
    public Rent mapToRentalDto(final RentDto rentDto) {
        return new Rent(rentDto.getUserId(), rentDto.getSpecimenId(), rentDto.getRentDate(), rentDto.getReturnDate());
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

    public User mapToUser (final UserDto userDto){
        return new User(userDto.getName(), userDto.getSurname());
    }

    public UserDto mapToUserDto (final User user) {
        return new UserDto(user.getName(), user.getSurname());
    }
}

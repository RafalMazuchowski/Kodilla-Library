package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {
    private long userId;
    private long specimenId;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
}
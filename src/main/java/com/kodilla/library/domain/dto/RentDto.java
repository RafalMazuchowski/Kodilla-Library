package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {
    private Long specimenId;
    private Long userId;
    private Date rentDate;
    private Date returnDate;
}
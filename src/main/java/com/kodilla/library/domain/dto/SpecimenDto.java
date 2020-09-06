package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.SpecimenStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpecimenDto {
    private SpecimenStatus status;
    private Long bookId;
}
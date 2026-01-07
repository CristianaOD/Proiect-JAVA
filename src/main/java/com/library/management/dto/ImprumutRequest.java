package com.library.management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImprumutRequest {

    @NotNull
    private Integer utilizatorId;

    @NotNull
    private Integer carteId;
}




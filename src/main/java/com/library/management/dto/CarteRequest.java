package com.library.management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarteRequest {

    @NotBlank
    @Size(max = 100)
    private String titlu;

    @NotNull
    @Min(0)
    private Integer stoc;

    @NotNull
    private Integer autorId;

    @NotNull
    private Integer categorieId;
}




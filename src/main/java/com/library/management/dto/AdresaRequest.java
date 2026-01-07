package com.library.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdresaRequest {

    @NotBlank
    @Size(max = 45)
    private String oras;

    @NotBlank
    @Size(max = 10)
    private String strada;

    @Size(max = 10)
    private String codPostal;
}



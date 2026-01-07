package com.library.management.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UtilizatorRequest {

    @NotBlank
    @Size(max = 45)
    private String nume;

    @NotBlank
    @Size(max = 45)
    private String prenume;

    @NotBlank
    @Email
    @Size(max = 45)
    private String email;

    @NotBlank
    @Size(max = 45)
    private String telefon;

    @NotNull
    @Valid
    private AdresaRequest adresa;
}



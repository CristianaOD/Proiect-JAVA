package com.library.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UtilizatorRequest {
    @Schema(description = "Nume utilizator", example = "Pop")
    @NotBlank
    @Size(max = 45)
    private String nume;

    @Schema(description = "Prenume utilizator", example = "Ana")
    @NotBlank
    @Size(max = 45)
    private String prenume;

    @Schema(description = "Email utilizator", example = "ana.pop@email.com")
    @NotBlank
    @Email
    @Size(max = 45)
    private String email;

    @Schema(description = "Telefon utilizator", example = "0700000000")
    @NotBlank
    @Pattern(regexp = "^0\\d{9}$", message = "Telefonul trebuie sa aiba 10 cifre si sa inceapa cu 0")
    @Size(max = 45)
    private String telefon;

    @Schema(description = "Adresa utilizatorului")
    @NotNull
    @Valid
    private AdresaRequest adresa;
}



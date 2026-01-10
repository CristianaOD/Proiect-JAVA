package com.library.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdresaRequest {

    @Schema(description = "Oras", example = "Bucuresti")
    @NotBlank
    @Size(max = 45)
    private String oras;

    @Schema(description = "Strada", example = "Strada 1")
    @NotBlank
    @Size(max = 10)
    private String strada;

    @Schema(description = "Cod postal (optional)", example = "010101")
    @Size(max = 10)
    private String codPostal;
}



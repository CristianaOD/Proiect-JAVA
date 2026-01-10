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
public class CategorieRequest {

    @Schema(description = "Nume categorie", example = "Fictiune")
    @NotBlank
    @Size(max = 100)
    private String nume;
}



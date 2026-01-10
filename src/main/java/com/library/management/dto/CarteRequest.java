package com.library.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Titlu carte", example = "Luceafarul")
    @NotBlank
    @Size(max = 100)
    private String titlu;

    @Schema(description = "Stoc disponibil", example = "5")
    @NotNull
    @Min(0)
    private Integer stoc;

    @Schema(description = "ID autor", example = "1")
    @NotNull
    private Integer autorId;

    @Schema(description = "ID categorie", example = "1")
    @NotNull
    private Integer categorieId;
}




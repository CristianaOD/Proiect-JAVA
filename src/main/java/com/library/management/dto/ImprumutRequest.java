package com.library.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImprumutRequest {

    @Schema(description = "ID utilizator", example = "1")
    @NotNull
    private Integer utilizatorId;

    @Schema(description = "ID carte", example = "1")
    @NotNull
    private Integer carteId;
}




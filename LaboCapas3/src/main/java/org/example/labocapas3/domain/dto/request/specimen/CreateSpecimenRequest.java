package org.example.labocapas3.domain.dto.request.specimen;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSpecimenRequest {
    @NotBlank(message = "Specimen name cannot be empty.")
    private String name;

    @NotBlank(message = "Description is required.")
    private String description;

    private String location;

    @NotNull(message = "Danger level is required.")
    @Min(1) @Max(10)
    private Integer dangerLevel;
}
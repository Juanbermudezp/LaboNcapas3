package org.example.labocapas3.domain.dto.response.specimen;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecimenResponse {
    private String name;
    private String description;
    private Integer dangerLevel;
}
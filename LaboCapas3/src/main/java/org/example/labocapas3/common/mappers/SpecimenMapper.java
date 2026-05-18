package org.example.labocapas3.common.mappers;

import org.example.labocapas3.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.labocapas3.domain.dto.response.specimen.PageableResponse;
import org.example.labocapas3.domain.dto.response.specimen.SpecimenResponse;
import org.example.labocapas3.domain.entities.Specimen;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SpecimenMapper {
    public Specimen toEntityCreate(CreateSpecimenRequest request) {
        return Specimen.builder()
                .name(request.getName())
                .description(request.getDescription())
                .location(request.getLocation())
                .dangerLevel(request.getDangerLevel())
                .build();
    }

    public SpecimenResponse toDto(Specimen specimen) {
        return SpecimenResponse.builder()
                .name(specimen.getName())
                .description(specimen.getDescription())
                .dangerLevel(specimen.getDangerLevel())
                .build();
    }

    public PageableResponse<SpecimenResponse> toPageableResponse(Page<Specimen> page) {
        List<SpecimenResponse> content = page.getContent().stream()
                .map(this::toDto)
                .toList();

        return PageableResponse.<SpecimenResponse>builder()
                .content(content)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}

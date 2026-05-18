package org.example.labocapas3.services;

import org.example.labocapas3.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.labocapas3.domain.dto.response.specimen.PageableResponse;
import org.example.labocapas3.domain.dto.response.specimen.SpecimenResponse;
import java.util.UUID;

public interface SpecimenService {
    SpecimenResponse createSpecimen(CreateSpecimenRequest request);
    PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder);
    SpecimenResponse getSpecimenById(UUID id);
}

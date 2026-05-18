package org.example.labocapas3.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.labocapas3.domain.dto.response.specimen.PageableResponse;
import org.example.labocapas3.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.labocapas3.domain.dto.response.specimen.SpecimenResponse;
import org.example.labocapas3.domain.entities.Specimen;
import org.example.labocapas3.common.mappers.SpecimenMapper;
import org.example.labocapas3.excepions.ResourceNotFoundException;
import org.example.labocapas3.repositories.SpecimenRepository;
import org.example.labocapas3.services.SpecimenService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return specimenMapper.toDto(specimenRepository.save(specimenMapper.toEntityCreate(request)));
    }

    @Override
    public PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Specimen> specimenPage = specimenRepository.findAll(pageable);

        if (specimenPage.isEmpty()) {
            throw new ResourceNotFoundException("No specimens are registered in Hyrule");
        }

        return specimenMapper.toPageableResponse(specimenPage);
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenMapper.toDto(specimenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen not found in Sheikah Slate records")));
    }
}
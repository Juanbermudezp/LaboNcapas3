package org.example.labocapas3.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.labocapas3.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.labocapas3.domain.dto.response.GeneralResponse;
import org.example.labocapas3.services.impl.SpecimenServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/specimen")
@RequiredArgsConstructor
public class SpecimenController {
    private final SpecimenServiceImpl specimenService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createSpecimen(@RequestBody @Valid CreateSpecimenRequest request) {
        return buildResponse(
                "Specimen created successfully in Sheikah Register",
                HttpStatus.CREATED,
                specimenService.createSpecimen(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity<GeneralResponse> getAllSpecimens(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        return buildResponse(
                "Specimens retrieved successfully",
                HttpStatus.OK,
                specimenService.getAllSpecimens(page, size, sortBy, sortOrder)
        );
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<GeneralResponse> getSpecimenById(@PathVariable UUID id) {
        return buildResponse(
                "Specimen record found",
                HttpStatus.OK,
                specimenService.getSpecimenById(id)
        );
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data){
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity
                .status(status)
                .body(GeneralResponse.builder()
                        .message(message)
                        .uri(uri)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
                );
    }
}

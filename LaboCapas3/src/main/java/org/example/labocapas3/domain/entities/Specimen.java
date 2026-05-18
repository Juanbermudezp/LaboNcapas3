package org.example.labocapas3.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "Specimen")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;
    private String location;
    private Integer dangerLevel;
}
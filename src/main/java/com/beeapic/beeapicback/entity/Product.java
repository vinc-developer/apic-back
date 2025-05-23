package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PRODUCT", discriminatorType = DiscriminatorType.STRING)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nomProduit;

    @NotNull
    private Double prix;

    @NotNull
    private Long poids;

    @NotNull
    private Long quantiteCreer;

    @NotNull
    private Long quantiteVendu;

    @NotNull
    private String numeroLot;

    @NotNull
    private LocalDate DDM;

    @NotNull
    private LocalDate dateConditionnement;

    @ManyToOne
    @JoinColumn(name = "extraction_id", nullable = false)
    private Extraction extraction;
}

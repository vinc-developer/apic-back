package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Extraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate dateExtraction;

    private String nomMieler;

    private Long qteExtraite;

    private Long qteHausse;

    @OneToMany(mappedBy = "extraction", cascade = CascadeType.ALL)
    private List<Recolte> recoltes;

    @OneToMany(mappedBy = "extraction", cascade = CascadeType.ALL)
    private List<Product> products;
}

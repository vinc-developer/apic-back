package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String name;

    private String environnement;

    @OneToOne
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @OneToMany(mappedBy = "rucher", cascade = CascadeType.ALL)
    private List<Ruche> ruches;

    @ManyToOne
    @JoinColumn(name = "apiculteur_id", nullable = false)
    private Apiculteur apiculteur;
}

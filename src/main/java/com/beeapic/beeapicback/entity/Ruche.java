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
public class Ruche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String name;

    private String typeruche;

    private String beetype;

    @OneToMany(mappedBy = "ruche", cascade = CascadeType.ALL)
    private List<Recolte> recoltes;

    @ManyToOne
    @JoinColumn(name = "rucher_id", nullable = false)
    private Rucher rucher;
}

package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String name;

    //https://thorben-janssen.com/hibernate-jpa-date-and-time/
    @NotNull
    private LocalDate recoltedate;

    @NotNull
    private Long recoltekg;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "ruche_id", nullable = false)
    private Ruche ruche;
}

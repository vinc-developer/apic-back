package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
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
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String numeroCommande;

    private Double prixTotal;

    private LocalDate dateCommande;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusCommande statusCommande;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}

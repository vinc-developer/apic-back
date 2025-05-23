package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String telephone;

    @OneToOne
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    /*@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;*/

}

package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class Apiculteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String lastname;

    @NotBlank
    private String firstname;

    private String siret;

    @NotBlank
    private String napi;

    @NotBlank
    @Email
    private String email;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "^([0][1-9]([0-9][0-9]){4})$")
    private String telephone;

    private String password;

    @OneToOne
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @OneToMany(mappedBy = "apiculteur", cascade = CascadeType.ALL)
    private List<Rucher> ruchers;
}

package org.example.pitstop_api.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "Pitstop_Gerente")
@Entity(name = "Gerente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Gerente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGerente;
    @NotNull
    private String nome;
    @NotNull
    private String senha;

    @OneToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;
}

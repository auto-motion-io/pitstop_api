package org.example.pitstop_api.domain.entities.pitstop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.pitstop_api.application.dtos.GerenteDTO;
import org.example.pitstop_api.domain.entities.Oficina;

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

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

    @OneToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;


    public Gerente(GerenteDTO gerenteDTO, Oficina oficina) {
    }
}

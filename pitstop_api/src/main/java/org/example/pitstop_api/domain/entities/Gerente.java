package org.example.pitstop_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.pitstop_api.application.dtos.GerenteDTO;

@Table(name = "Pitstop_Gerente")
@Entity(name = "Gerente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Gerente {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idGerente;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String senha;

    @ManyToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;

    public Gerente(GerenteDTO gerenteDTO, Oficina oficina) {
        this.nome = gerenteDTO.nome();
        this.email = gerenteDTO.email();
        this.senha = gerenteDTO.senha();
        this.oficina = oficina;
    }
}

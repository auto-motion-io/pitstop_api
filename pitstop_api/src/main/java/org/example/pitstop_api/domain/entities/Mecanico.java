package org.example.pitstop_api.domain.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "Pitstop_Mecanico")
@Entity(name = "Mecanico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mecanico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMecanico;
    @NotNull
    private String nome;
    private String telefone;

    @OneToOne
    @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficinaMecanico;


}

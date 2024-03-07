package org.example.pitstop_api.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Table(name = "Pitstop_Oficina")
@Entity(name = "Oficina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Oficina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOficina;
    @NotNull
    private String nome;
    @NotNull
    private String cnpj;
    @NotNull
    private String cep;
    @NotNull
    private String numero;
    private String complemento;
}

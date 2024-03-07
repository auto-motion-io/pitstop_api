package org.example.pitstop_api.domain.entities.buscar;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Buscar_Usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
}

package org.example.pitstop_api.domain.entities.pitstop;

import jakarta.persistence.*;
import lombok.*;
import org.example.pitstop_api.application.dtos.RequestClienteDTO;

@Table(name = "Pitstop_Cliente")
@Entity(name = "Cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(RequestClienteDTO clienteDTO) {
        this.nome = clienteDTO.nome();
        this.telefone = clienteDTO.telefone();
        this.email = clienteDTO.email();
    }

}

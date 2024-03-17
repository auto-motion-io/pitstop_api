package org.motion.motion_api.domain.entities.pitstop;

import jakarta.persistence.*;
import lombok.*;
import org.motion.motion_api.application.dtos.CreateClienteDTO;

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
    public Cliente(CreateClienteDTO novoClienteDTO) {
    }
}

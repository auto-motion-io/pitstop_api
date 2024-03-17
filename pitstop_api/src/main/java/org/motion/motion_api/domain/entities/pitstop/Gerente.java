package org.motion.motion_api.domain.entities.pitstop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.application.dtos.CreateGerenteDTO;
import org.motion.motion_api.domain.entities.Oficina;

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
    @NotNull @NotBlank
    private String nome;
    @NotNull @NotBlank
    private String sobrenome;
    @NotNull @NotBlank
    private String email;
    private String senha;

    @Column(columnDefinition = "varchar(255) default 'PENDENTE'")
    private String status;

    @OneToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;


    public Gerente(CreateGerenteDTO createGerenteDTO, Oficina oficina) {
        this.nome = createGerenteDTO.nome();
        this.sobrenome = createGerenteDTO.sobrenome();
        this.email = createGerenteDTO.email();
        this.senha = null;
        this.status = "PENDENTE";
        this.oficina = oficina;
    }
}

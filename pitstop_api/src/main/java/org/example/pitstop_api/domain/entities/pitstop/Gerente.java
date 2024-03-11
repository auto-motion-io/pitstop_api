package org.example.pitstop_api.domain.entities.pitstop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.pitstop_api.application.dtos.CreateGerenteDTO;
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

    @Column(columnDefinition = "varchar(255) default 'PENDENTE'", nullable = true)
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

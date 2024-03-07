package org.example.pitstop_api.domain.entities.pitstop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.pitstop_api.domain.entities.Oficina;

import java.time.LocalDate;


@Table(name = "Pitstop_Tarefa")
@Entity(name = "Tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tarefa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarefa;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private String prioridade;
    private String status;

    @OneToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;
}

package org.motion.motion_api.domain.entities.pitstop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.domain.entities.Oficina;

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
    private LocalDate dtDeadline;
    private String prioridade;
    private String status;

    @ManyToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;
}

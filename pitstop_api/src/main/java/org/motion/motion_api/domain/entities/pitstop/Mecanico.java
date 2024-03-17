package org.motion.motion_api.domain.entities.pitstop;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.domain.entities.Oficina;

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

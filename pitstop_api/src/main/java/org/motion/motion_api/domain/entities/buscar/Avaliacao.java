package org.motion.motion_api.domain.entities.buscar;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.domain.entities.Oficina;
@Table(name = "Buscar_Avaliacao")
@Entity(name = "Avaliacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;
    private Double nota;
    private String comentario;
    @OneToOne
    @JoinColumn(name = "fkUsuario") @NotNull
    private Usuario usuarioAvaliacao;

    @OneToOne
    @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficinaAvaliacao;


}

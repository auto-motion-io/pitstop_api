package org.motion.motion_api.domain.entities.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.application.dtos.CreateServicoDTO;
import org.motion.motion_api.domain.entities.Oficina;

import java.util.HashSet;
import java.util.Set;

@Table(name = "Pitstop_Servico")
@Entity(name = "Servico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Servico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServico;
    private String nome;
    private String descricao;
    private Double valorServico;
    private String garantia;



    @ManyToOne @JoinColumn(name = "fkOficina") @NotNull
    @JsonIgnore
    private Oficina oficina;
    @ManyToMany(mappedBy = "servicos")
    private Set<OrdemDeServico> ordensDeServico = new HashSet<>();

    public Servico(CreateServicoDTO createServicoDTO, Oficina oficina) {
        this.nome = createServicoDTO.nome();
        this.descricao = createServicoDTO.descricao();
        this.valorServico = createServicoDTO.valorServico();
        this.garantia = createServicoDTO.garantia();
        this.oficina = oficina;
    }
}


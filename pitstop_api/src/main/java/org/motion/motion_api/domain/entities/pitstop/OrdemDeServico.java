package org.motion.motion_api.domain.entities.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import org.motion.motion_api.application.dtos.ordemDeServico.CreateOrdemDeServicoDTO;
import org.motion.motion_api.domain.entities.Oficina;

import java.time.LocalDate;
import java.util.*;

@Table(name = "Pitstop_OrdemDeServico")
@Entity(name = "OrdemDeServico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdemDeServico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdem;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private Double desconto;
    private Double valorTotal;
    private String token;
    private String tipoOs;
    private String garantia;
    private String observacoes;


    @ManyToOne @JoinColumn(name = "idOficina")
    @JsonIgnore
    private Oficina oficina;
    @ManyToOne @JoinColumn(name = "idVeiculo")
    @JsonIgnore
    private Veiculo veiculo;
    @ManyToOne @JoinColumn(name = "idMecanico")
    @JsonIgnore
    private Mecanico mecanico;
    @ManyToOne @JoinColumn(name = "idCliente")
    @JsonIgnore
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ProdutoEstoque> produtos = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Servico> servicos = new HashSet<>();
}

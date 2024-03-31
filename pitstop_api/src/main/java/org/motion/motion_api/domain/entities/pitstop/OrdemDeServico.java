package org.motion.motion_api.domain.entities.pitstop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.application.dtos.CreateOrdemDeServicoDTO;
import org.motion.motion_api.domain.entities.Oficina;

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
    private UUID uuid;
    private String token;
    private Date dataInicio;
    private Date dataFim;
    private String observacoes;
    private Double desconto;
    private String status;
    private Double valorTotal;
    private String garantia;


    @OneToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;
    @OneToOne @JoinColumn(name = "fkVeiculo")
    private Veiculo veiculo;
    @OneToOne @JoinColumn(name = "fkMecanico")
    private Mecanico mecanico;

    @ManyToMany
    @JoinTable(name = "ordem_produto",
            joinColumns = @JoinColumn(name = "ordem_id"),
            inverseJoinColumns = @JoinColumn(name = "fkProdutoEstoque"))
    private Set<ProdutoEstoque> produtos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ordem_servico",
            joinColumns = @JoinColumn(name = "ordem_id"),
            inverseJoinColumns = @JoinColumn(name = "fkServico"))
    private Set<Servico> servicos = new HashSet<>();

    public OrdemDeServico(CreateOrdemDeServicoDTO novaOrdemDeServicoDTO, Oficina oficina, Veiculo veiculo, Mecanico mecanico, ProdutoEstoque produtoEstoque) {
        this.dataInicio = new Date();
        this.dataFim = new Date();
        this.observacoes = novaOrdemDeServicoDTO.descricao();
        this.status = novaOrdemDeServicoDTO.status();
        this.oficina = oficina;
        this.garantia = novaOrdemDeServicoDTO.garantia();
        this.veiculo = veiculo;
        this.mecanico = mecanico;
    }
}

package org.motion.motion_api.domain.entities.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private Double desconto;
    private Double valorTotal;
    private String token;
    private String tipoOs;
    private String garantia;
    private String observacoes;


    @ManyToOne @JoinColumn(name = "fkOficina")

    private Oficina oficina;
    @ManyToOne @JoinColumn(name = "fkVeiculo")

    private Veiculo veiculo;
    @ManyToOne @JoinColumn(name = "fkMecanico")

    private Mecanico mecanico;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProdutoEstoque> produtos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Servico> servicos = new ArrayList<>();
}

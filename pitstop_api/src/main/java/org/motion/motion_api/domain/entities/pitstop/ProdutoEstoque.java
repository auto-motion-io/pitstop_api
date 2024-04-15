package org.motion.motion_api.domain.entities.pitstop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.application.dtos.produtoEstoque.CreateProdutoEstoqueDTO;
import org.motion.motion_api.domain.entities.Oficina;

import java.util.HashSet;
import java.util.Set;

@Table(name = "Pitstop_ProdutoEstoque")
@Entity(name = "ProdutoEstoque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutoEstoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    private String nome;
    private Integer quantidade;
    private Double valorComMaoObra;
    private Double valorCompra;
    private Double valorVenda;
    private String localizacao;
    private String garantia;

    @ManyToOne @JoinColumn(name = "fkOficina") @NotNull
    @JsonIgnore
    private Oficina oficina;

    public ProdutoEstoque(CreateProdutoEstoqueDTO createProdutoEstoqueDTO, Oficina oficina) {
        this.nome = createProdutoEstoqueDTO.nome();
        this.quantidade = createProdutoEstoqueDTO.quantidade();
        this.valorVenda = createProdutoEstoqueDTO.valorVenda();
        this.oficina = oficina;
    }
}

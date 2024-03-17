package org.motion.motion_api.domain.entities.pitstop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.domain.entities.Oficina;

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
    private String descricao;
    private Double quantidade;
    private String localizacao;
    private Double valorCompra;
    private Double valorVenda;
    private Double valorComMaoObra;

    @ManyToOne @JoinColumn(name = "fkOficina") @NotNull
    private Oficina oficina;
}

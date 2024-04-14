package org.motion.motion_api.domain.entities.pitstop;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Pitstop_Ordem_Produto_Servico")
@Entity(name = "OrdemProdutosServicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdemProdutosServicos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdemProdutoServico;

}

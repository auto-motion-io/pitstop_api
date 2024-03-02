package org.example.pitstop_api.domain.entities;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "Pitstop_Veiculo")
@Entity(name = "Veiculo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Veiculos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private String cor;

    @ManyToOne @JoinColumn(name = "fkCliente")
    private Cliente cliente;
}

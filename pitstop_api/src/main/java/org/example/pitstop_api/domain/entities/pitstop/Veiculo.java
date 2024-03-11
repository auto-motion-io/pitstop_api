package org.example.pitstop_api.domain.entities.pitstop;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.pitstop_api.application.dtos.CreateVeiculoDTO;

@Table(name = "Pitstop_Veiculo")
@Entity(name = "Veiculo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private String cor;

    @ManyToOne @JoinColumn(name = "fkCliente") @NotNull
    private Cliente cliente;

    public Veiculo(CreateVeiculoDTO novoVeiculoDTO, Cliente cliente) {
        this.placa = novoVeiculoDTO.placa();
        this.marca = novoVeiculoDTO.marca();
        this.modelo = novoVeiculoDTO.modelo();
        this.anoFabricacao = novoVeiculoDTO.ano();
        this.cor = novoVeiculoDTO.cor();
        this.cliente = cliente;
    }
}

package org.example.pitstop_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Table(name = "Pitstop_Oficina")
@Entity(name = "Oficina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Oficina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOficina;
    @NotNull
    private String nome;
    private String cep;
    private String numero;
    private String complemento;

    @OneToMany(mappedBy = "oficina") @JsonIgnore
    private List<Gerente> gerentes;


    @Override
    public String toString() {
        return "Oficina{" +
                "idOficina=" + idOficina +
                ", nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}

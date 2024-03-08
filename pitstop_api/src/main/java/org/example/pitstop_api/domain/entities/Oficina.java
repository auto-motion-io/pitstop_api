package org.example.pitstop_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer idOficina;
    @NotNull
    private String nome;
    @NotNull
    private String cnpj;
    @NotNull
    private String cep;
    @NotNull
    private String numero;
    private String complemento;

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

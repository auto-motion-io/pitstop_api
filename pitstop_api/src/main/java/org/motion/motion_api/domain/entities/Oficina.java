package org.motion.motion_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.motion.motion_api.domain.entities.pitstop.Gerente;

@Table(name = "Pitstop_Oficina")
@Entity(name = "Oficina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Oficina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer idOficina;
    @NotNull @NotBlank
    private String nome;
    @NotNull @NotBlank
    private String cnpj;
    @NotNull @NotBlank
    private String cep;
    @NotNull @NotBlank
    private String numero;
    private String complemento;
    private boolean hasBuscar;

    @OneToOne(mappedBy = "oficina", cascade = CascadeType.ALL) @JsonIgnore
    private Gerente gerente;
}

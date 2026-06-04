package br.com.gscarbono.gs_carbono_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "emissoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Fonte de emissão é obrigatória")
    @Column(name = "fonte_emissao", nullable = false, length = 150)
    private String fonteEmissao;

    @NotNull(message = "Quantidade de CO2 é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    @Column(name = "quantidade_co2_toneladas", nullable = false)
    private Double quantidadeCo2Toneladas;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "tipo_gas", length = 50)
    private String tipoGas;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id", nullable = false)
    private Setor setor;

    @PrePersist
    public void prePersist() {
        this.dataRegistro = LocalDateTime.now();
        if (this.dataEmissao == null) this.dataEmissao = LocalDate.now();
    }
}
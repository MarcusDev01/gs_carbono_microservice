package br.com.gscarbono.gs_carbono_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "relatorios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotNull(message = "Período de início é obrigatório")
    @Column(name = "periodo_inicio", nullable = false)
    private LocalDate periodoInicio;

    @NotNull(message = "Período de fim é obrigatório")
    @Column(name = "periodo_fim", nullable = false)
    private LocalDate periodoFim;

    @Column(name = "total_emissoes_toneladas")
    private Double totalEmissoesToneladas;

    @Column(name = "total_reducoes_toneladas")
    private Double totalReducoesToneladas;

    @Column(name = "saldo_carbono")
    private Double saldoCarbono;

    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    private StatusRelatorio status;

    @Column(name = "data_geracao")
    private LocalDateTime dataGeracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        this.dataGeracao = LocalDateTime.now();
        if (this.status == null) this.status = StatusRelatorio.RASCUNHO;
    }

    public enum StatusRelatorio {
        RASCUNHO,
        EM_ANALISE,
        FINALIZADO,
        PUBLICADO
    }
}
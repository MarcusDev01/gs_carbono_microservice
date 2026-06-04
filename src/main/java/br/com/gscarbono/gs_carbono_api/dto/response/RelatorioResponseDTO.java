package br.com.gscarbono.gs_carbono_api.dto.response;

import br.com.gscarbono.gs_carbono_api.entity.Relatorio;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioResponseDTO {
    private Long id;
    private String titulo;
    private LocalDate periodoInicio;
    private LocalDate periodoFim;
    private Double totalEmissoesToneladas;
    private Double totalReducoesToneladas;
    private Double saldoCarbono;
    private String observacoes;
    private Relatorio.StatusRelatorio status;
    private LocalDateTime dataGeracao;
    private Long usuarioId;
    private String usuarioNome;
}
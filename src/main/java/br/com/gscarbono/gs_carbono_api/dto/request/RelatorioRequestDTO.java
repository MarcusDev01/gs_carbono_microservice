package br.com.gscarbono.gs_carbono_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotNull(message = "Período de início é obrigatório")
    private LocalDate periodoInicio;

    @NotNull(message = "Período de fim é obrigatório")
    private LocalDate periodoFim;

    private Double totalEmissoesToneladas;
    private Double totalReducoesToneladas;
    private Double saldoCarbono;
    private String observacoes;

    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;
}
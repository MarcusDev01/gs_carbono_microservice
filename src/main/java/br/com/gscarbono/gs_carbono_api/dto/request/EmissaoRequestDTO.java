package br.com.gscarbono.gs_carbono_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissaoRequestDTO {

    @NotBlank(message = "Fonte de emissão é obrigatória")
    private String fonteEmissao;

    @NotNull(message = "Quantidade de CO2 é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    private Double quantidadeCo2Toneladas;

    private LocalDate dataEmissao;
    private String tipoGas;
    private String descricao;
    private Double latitude;
    private Double longitude;

    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "ID do setor é obrigatório")
    private Long setorId;
}
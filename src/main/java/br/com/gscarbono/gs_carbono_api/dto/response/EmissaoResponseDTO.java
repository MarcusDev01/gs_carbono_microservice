package br.com.gscarbono.gs_carbono_api.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissaoResponseDTO {
    private Long id;
    private String fonteEmissao;
    private Double quantidadeCo2Toneladas;
    private LocalDate dataEmissao;
    private String tipoGas;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private LocalDateTime dataRegistro;
    private Long usuarioId;
    private String usuarioNome;
    private Long setorId;
    private String setorNome;
}

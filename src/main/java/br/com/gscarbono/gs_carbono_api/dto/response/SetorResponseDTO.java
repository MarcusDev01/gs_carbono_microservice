package br.com.gscarbono.gs_carbono_api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetorResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String codigoSetor;
    private Double fatorEmissaoMedio;
    private Double metaReducaoAnual;
    private Boolean ativo;
}
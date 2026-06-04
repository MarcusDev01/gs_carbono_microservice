package br.com.gscarbono.gs_carbono_api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String organizacao;
    private String cargo;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
}
package br.com.gscarbono.gs_carbono_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "setores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do setor é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(name = "codigo_setor", unique = true, length = 20)
    private String codigoSetor;

    @Column(name = "fator_emissao_medio")
    private Double fatorEmissaoMedio;

    @Column(name = "meta_reducao_anual")
    private Double metaReducaoAnual;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Emissao> emissoes;

    @PrePersist
    public void prePersist() {
        if (this.ativo == null) this.ativo = true;
    }
}
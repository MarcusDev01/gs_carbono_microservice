package br.com.gscarbono.gs_carbono_api.repository;

import br.com.gscarbono.gs_carbono_api.entity.Emissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmissaoRepository extends JpaRepository<Emissao, Long> {
    List<Emissao> findByUsuarioId(Long usuarioId);
    List<Emissao> findBySetorId(Long setorId);
    List<Emissao> findByDataEmissaoBetween(LocalDate inicio, LocalDate fim);

    @Query("SELECT SUM(e.quantidadeCo2Toneladas) FROM Emissao e WHERE e.setor.id = :setorId")
    Double sumCo2BySetorId(@Param("setorId") Long setorId);
}
package br.com.gscarbono.gs_carbono_api.repository;

import br.com.gscarbono.gs_carbono_api.entity.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
    List<Relatorio> findByUsuarioId(Long usuarioId);
    List<Relatorio> findByStatus(Relatorio.StatusRelatorio status);
}
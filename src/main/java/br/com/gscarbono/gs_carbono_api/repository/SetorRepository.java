package br.com.gscarbono.gs_carbono_api.repository;

import br.com.gscarbono.gs_carbono_api.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    Optional<Setor> findByCodigoSetor(String codigoSetor);
    List<Setor> findByAtivoTrue();
    boolean existsByCodigoSetor(String codigoSetor);
}
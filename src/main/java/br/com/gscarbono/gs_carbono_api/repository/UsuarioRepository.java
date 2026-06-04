// ===== UsuarioRepository.java =====
package br.com.gscarbono.gs_carbono_api.repository;

import br.com.gscarbono.gs_carbono_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
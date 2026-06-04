package br.com.gscarbono.gs_carbono_api.controller;

import br.com.gscarbono.gs_carbono_api.dto.request.SetorRequestDTO;
import br.com.gscarbono.gs_carbono_api.dto.response.SetorResponseDTO;
import br.com.gscarbono.gs_carbono_api.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/setores")
@RequiredArgsConstructor
@Tag(name = "Setores", description = "Gerenciamento de setores industriais")
public class SetorController {

    private final SetorService setorService;

    @GetMapping
    @Operation(summary = "Listar todos os setores")
    public ResponseEntity<List<SetorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(setorService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar setor por ID")
    public ResponseEntity<SetorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(setorService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar novo setor")
    public ResponseEntity<SetorResponseDTO> criar(@RequestBody @Valid SetorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(setorService.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar setor")
    public ResponseEntity<SetorResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SetorRequestDTO dto) {
        return ResponseEntity.ok(setorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar setor")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        setorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
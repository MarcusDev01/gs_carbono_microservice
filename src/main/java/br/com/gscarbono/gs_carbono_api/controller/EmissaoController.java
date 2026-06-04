package br.com.gscarbono.gs_carbono_api.controller;

import br.com.gscarbono.gs_carbono_api.dto.request.EmissaoRequestDTO;
import br.com.gscarbono.gs_carbono_api.dto.response.EmissaoResponseDTO;
import br.com.gscarbono.gs_carbono_api.service.EmissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/emissoes")
@RequiredArgsConstructor
@Tag(name = "Emissões", description = "Gerenciamento de emissões de carbono")
public class EmissaoController {

    private final EmissaoService emissaoService;

    @GetMapping
    @Operation(summary = "Listar todas as emissões")
    public ResponseEntity<List<EmissaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(emissaoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar emissão por ID")
    public ResponseEntity<EmissaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emissaoService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar emissões por usuário")
    public ResponseEntity<List<EmissaoResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(emissaoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/setor/{setorId}")
    @Operation(summary = "Listar emissões por setor")
    public ResponseEntity<List<EmissaoResponseDTO>> listarPorSetor(@PathVariable Long setorId) {
        return ResponseEntity.ok(emissaoService.listarPorSetor(setorId));
    }

    @PostMapping
    @Operation(summary = "Registrar nova emissão")
    public ResponseEntity<EmissaoResponseDTO> criar(@RequestBody @Valid EmissaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emissaoService.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar emissão")
    public ResponseEntity<EmissaoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EmissaoRequestDTO dto) {
        return ResponseEntity.ok(emissaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar emissão")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emissaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
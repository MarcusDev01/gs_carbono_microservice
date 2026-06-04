package br.com.gscarbono.gs_carbono_api.controller;

import br.com.gscarbono.gs_carbono_api.dto.request.RelatorioRequestDTO;
import br.com.gscarbono.gs_carbono_api.dto.response.RelatorioResponseDTO;
import br.com.gscarbono.gs_carbono_api.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/relatorios")
@RequiredArgsConstructor
@Tag(name = "Relatórios", description = "Gerenciamento de relatórios de carbono")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping
    @Operation(summary = "Listar todos os relatórios")
    public ResponseEntity<List<RelatorioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(relatorioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar relatório por ID")
    public ResponseEntity<RelatorioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(relatorioService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar relatórios por usuário")
    public ResponseEntity<List<RelatorioResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(relatorioService.listarPorUsuario(usuarioId));
    }

    @PostMapping
    @Operation(summary = "Criar novo relatório")
    public ResponseEntity<RelatorioResponseDTO> criar(@RequestBody @Valid RelatorioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioService.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar relatório")
    public ResponseEntity<RelatorioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RelatorioRequestDTO dto) {
        return ResponseEntity.ok(relatorioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar relatório")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
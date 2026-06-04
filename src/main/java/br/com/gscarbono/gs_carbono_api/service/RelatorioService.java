package br.com.gscarbono.gs_carbono_api.service;

import br.com.gscarbono.gs_carbono_api.dto.request.RelatorioRequestDTO;
import br.com.gscarbono.gs_carbono_api.dto.response.RelatorioResponseDTO;
import br.com.gscarbono.gs_carbono_api.entity.Relatorio;
import br.com.gscarbono.gs_carbono_api.entity.Usuario;
import br.com.gscarbono.gs_carbono_api.repository.RelatorioRepository;
import br.com.gscarbono.gs_carbono_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public List<RelatorioResponseDTO> listarTodos() {
        return relatorioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RelatorioResponseDTO buscarPorId(Long id) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado com id: " + id));
        return toResponseDTO(relatorio);
    }

    public List<RelatorioResponseDTO> listarPorUsuario(Long usuarioId) {
        return relatorioRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RelatorioResponseDTO criar(RelatorioRequestDTO dto) {
        if (dto.getPeriodoFim().isBefore(dto.getPeriodoInicio())) {
            throw new RuntimeException("Período de fim não pode ser anterior ao período de início");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + dto.getUsuarioId()));

        Relatorio relatorio = modelMapper.map(dto, Relatorio.class);
        relatorio.setUsuario(usuario);
        relatorio.setId(null);

        return toResponseDTO(relatorioRepository.save(relatorio));
    }

    public RelatorioResponseDTO atualizar(Long id, RelatorioRequestDTO dto) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado com id: " + id));

        if (dto.getPeriodoFim().isBefore(dto.getPeriodoInicio())) {
            throw new RuntimeException("Período de fim não pode ser anterior ao período de início");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + dto.getUsuarioId()));

        relatorio.setTitulo(dto.getTitulo());
        relatorio.setPeriodoInicio(dto.getPeriodoInicio());
        relatorio.setPeriodoFim(dto.getPeriodoFim());
        relatorio.setTotalEmissoesToneladas(dto.getTotalEmissoesToneladas());
        relatorio.setTotalReducoesToneladas(dto.getTotalReducoesToneladas());
        relatorio.setSaldoCarbono(dto.getSaldoCarbono());
        relatorio.setObservacoes(dto.getObservacoes());
        relatorio.setUsuario(usuario);

        return toResponseDTO(relatorioRepository.save(relatorio));
    }

    public void deletar(Long id) {
        if (!relatorioRepository.existsById(id)) {
            throw new RuntimeException("Relatório não encontrado com id: " + id);
        }
        relatorioRepository.deleteById(id);
    }

    private RelatorioResponseDTO toResponseDTO(Relatorio relatorio) {
        RelatorioResponseDTO dto = modelMapper.map(relatorio, RelatorioResponseDTO.class);
        dto.setUsuarioId(relatorio.getUsuario().getId());
        dto.setUsuarioNome(relatorio.getUsuario().getNome());
        return dto;
    }
}
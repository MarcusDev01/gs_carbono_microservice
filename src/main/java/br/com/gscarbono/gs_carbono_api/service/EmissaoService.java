package br.com.gscarbono.gs_carbono_api.service;

import br.com.gscarbono.gs_carbono_api.dto.request.EmissaoRequestDTO;
import br.com.gscarbono.gs_carbono_api.dto.response.EmissaoResponseDTO;
import br.com.gscarbono.gs_carbono_api.entity.Emissao;
import br.com.gscarbono.gs_carbono_api.entity.Setor;
import br.com.gscarbono.gs_carbono_api.entity.Usuario;
import br.com.gscarbono.gs_carbono_api.repository.EmissaoRepository;
import br.com.gscarbono.gs_carbono_api.repository.SetorRepository;
import br.com.gscarbono.gs_carbono_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmissaoService {

    private final EmissaoRepository emissaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SetorRepository setorRepository;
    private final ModelMapper modelMapper;

    public List<EmissaoResponseDTO> listarTodos() {
        return emissaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EmissaoResponseDTO buscarPorId(Long id) {
        Emissao emissao = emissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emissão não encontrada com id: " + id));
        return toResponseDTO(emissao);
    }

    public List<EmissaoResponseDTO> listarPorUsuario(Long usuarioId) {
        return emissaoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EmissaoResponseDTO> listarPorSetor(Long setorId) {
        return emissaoRepository.findBySetorId(setorId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EmissaoResponseDTO criar(EmissaoRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Setor setor = setorRepository.findById(dto.getSetorId())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        Emissao emissao = new Emissao();

        emissao.setFonteEmissao(dto.getFonteEmissao());
        emissao.setQuantidadeCo2Toneladas(dto.getQuantidadeCo2Toneladas());
        emissao.setDataEmissao(dto.getDataEmissao());
        emissao.setTipoGas(dto.getTipoGas());
        emissao.setDescricao(dto.getDescricao());
        emissao.setLatitude(dto.getLatitude());
        emissao.setLongitude(dto.getLongitude());

        emissao.setUsuario(usuario);
        emissao.setSetor(setor);

        Emissao salva = emissaoRepository.save(emissao);

        return toResponseDTO(salva);
    }

    public EmissaoResponseDTO atualizar(Long id, EmissaoRequestDTO dto) {
        Emissao emissao = emissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emissão não encontrada com id: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + dto.getUsuarioId()));

        Setor setor = setorRepository.findById(dto.getSetorId())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com id: " + dto.getSetorId()));

        emissao.setFonteEmissao(dto.getFonteEmissao());
        emissao.setQuantidadeCo2Toneladas(dto.getQuantidadeCo2Toneladas());
        emissao.setDataEmissao(dto.getDataEmissao());
        emissao.setTipoGas(dto.getTipoGas());
        emissao.setDescricao(dto.getDescricao());
        emissao.setLatitude(dto.getLatitude());
        emissao.setLongitude(dto.getLongitude());
        emissao.setUsuario(usuario);
        emissao.setSetor(setor);

        return toResponseDTO(emissaoRepository.save(emissao));
    }

    public void deletar(Long id) {
        if (!emissaoRepository.existsById(id)) {
            throw new RuntimeException("Emissão não encontrada com id: " + id);
        }
        emissaoRepository.deleteById(id);
    }

    private EmissaoResponseDTO toResponseDTO(Emissao emissao) {
        EmissaoResponseDTO dto = modelMapper.map(emissao, EmissaoResponseDTO.class);
        dto.setUsuarioId(emissao.getUsuario().getId());
        dto.setUsuarioNome(emissao.getUsuario().getNome());
        dto.setSetorId(emissao.getSetor().getId());
        dto.setSetorNome(emissao.getSetor().getNome());
        return dto;
    }
}
package br.com.gscarbono.gs_carbono_api.service;

import br.com.gscarbono.gs_carbono_api.dto.request.SetorRequestDTO;
import br.com.gscarbono.gs_carbono_api.dto.response.SetorResponseDTO;
import br.com.gscarbono.gs_carbono_api.entity.Setor;
import br.com.gscarbono.gs_carbono_api.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SetorService {

    private final SetorRepository setorRepository;
    private final ModelMapper modelMapper;

    public List<SetorResponseDTO> listarTodos() {
        return setorRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, SetorResponseDTO.class))
                .collect(Collectors.toList());
    }

    public SetorResponseDTO buscarPorId(Long id) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com id: " + id));
        return modelMapper.map(setor, SetorResponseDTO.class);
    }

    public SetorResponseDTO criar(SetorRequestDTO dto) {
        if (dto.getCodigoSetor() != null && setorRepository.existsByCodigoSetor(dto.getCodigoSetor())) {
            throw new RuntimeException("Já existe um setor com o código: " + dto.getCodigoSetor());
        }
        Setor setor = modelMapper.map(dto, Setor.class);
        return modelMapper.map(setorRepository.save(setor), SetorResponseDTO.class);
    }

    public SetorResponseDTO atualizar(Long id, SetorRequestDTO dto) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado com id: " + id));

        setor.setNome(dto.getNome());
        setor.setDescricao(dto.getDescricao());
        setor.setCodigoSetor(dto.getCodigoSetor());
        setor.setFatorEmissaoMedio(dto.getFatorEmissaoMedio());
        setor.setMetaReducaoAnual(dto.getMetaReducaoAnual());

        return modelMapper.map(setorRepository.save(setor), SetorResponseDTO.class);
    }

    public void deletar(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new RuntimeException("Setor não encontrado com id: " + id);
        }
        setorRepository.deleteById(id);
    }
}
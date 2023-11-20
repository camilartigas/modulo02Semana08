package com.MiniProjetoCheckin.Projeto.Checkin.service;

import com.MiniProjetoCheckin.Projeto.Checkin.dto.FuncionarioDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.dto.PontoDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.model.Funcionario;
import com.MiniProjetoCheckin.Projeto.Checkin.model.Ponto;
import com.MiniProjetoCheckin.Projeto.Checkin.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Page<FuncionarioDTO> listarFuncionariosPaginados(int pagina, int tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageRequest);
        return funcionarios.map(this::converterParaDTO);
    }

    private FuncionarioDTO converterParaDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setCargo(funcionario.getCargo());
        funcionarioDTO.setSalario(funcionario.getSalario());

        List<PontoDTO> registrosPonto = converterRegistrosPontoParaDTO(funcionario.getRegistros());
        funcionarioDTO.setRegistrosPonto(registrosPonto);

        return funcionarioDTO;
    }

    public Funcionario criarFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(funcionarioDTO.getNome());
        novoFuncionario.setCargo(funcionarioDTO.getCargo());
        novoFuncionario.setSalario(funcionarioDTO.getSalario());

        return funcionarioRepository.save(novoFuncionario);
    }

    public FuncionarioDTO buscarDetalhesFuncionario(Long idFuncionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            List<PontoDTO> registrosPonto = converterRegistrosPontoParaDTO(funcionario.getRegistros());

            FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
            funcionarioDTO.setId(funcionario.getId());
            funcionarioDTO.setNome(funcionario.getNome());
            funcionarioDTO.setCargo(funcionario.getCargo());
            funcionarioDTO.setSalario(funcionario.getSalario());
            funcionarioDTO.setRegistrosPonto(registrosPonto);

            return funcionarioDTO;
        } else {
            return null;
        }
    }

    private List<PontoDTO> converterRegistrosPontoParaDTO(List<Ponto> registrosPonto) {
        List<PontoDTO> registrosPontoDTO = new ArrayList<>();
        for (Ponto registroPonto : registrosPonto) {
            registrosPontoDTO.add(converterRegistroPontoParaDTO(registroPonto));
        }
        return registrosPontoDTO;
    }
    private PontoDTO converterRegistroPontoParaDTO(Ponto registroPonto) {
        PontoDTO registroPontoDTO = new PontoDTO();
        registroPontoDTO.setId(registroPonto.getId());
        registroPontoDTO.setHorario(registroPonto.getHorario());
        registroPontoDTO.setTipo(registroPonto.getTipo());
        return registroPontoDTO;
    }

    public ResponseEntity<FuncionarioDTO> adicionarPontoParaFuncionario(Long idFuncionario, PontoDTO pontoDTO) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(idFuncionario);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();

            Ponto ponto = new Ponto();
            ponto.setFuncionario(funcionario);
            ponto.setHorario(pontoDTO.getHorario());
            ponto.setTipo(pontoDTO.getTipo());

            funcionario.getRegistros().add(ponto);

            funcionarioRepository.save(funcionario);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

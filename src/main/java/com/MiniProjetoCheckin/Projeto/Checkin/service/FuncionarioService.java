package com.MiniProjetoCheckin.Projeto.Checkin.service;

import com.MiniProjetoCheckin.Projeto.Checkin.dto.FuncionarioDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.model.Funcionario;
import com.MiniProjetoCheckin.Projeto.Checkin.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        return funcionarioDTO;
    }

    public Funcionario criarFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(funcionarioDTO.getNome());
        novoFuncionario.setCargo(funcionarioDTO.getCargo());
        novoFuncionario.setSalario(funcionarioDTO.getSalario());

        return funcionarioRepository.save(novoFuncionario);
    }
}

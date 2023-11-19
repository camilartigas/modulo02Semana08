package com.MiniProjetoCheckin.Projeto.Checkin.service;

import com.MiniProjetoCheckin.Projeto.Checkin.dto.FuncionarioDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.model.Funcionario;
import com.MiniProjetoCheckin.Projeto.Checkin.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario criarFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(funcionarioDTO.getNome());
        novoFuncionario.setCargo(funcionarioDTO.getCargo());
        novoFuncionario.setSalario(funcionarioDTO.getSalario());

        return funcionarioRepository.save(novoFuncionario);
    }
}

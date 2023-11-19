package com.MiniProjetoCheckin.Projeto.Checkin.controller;


import com.MiniProjetoCheckin.Projeto.Checkin.dto.FuncionarioDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.model.Funcionario;
import com.MiniProjetoCheckin.Projeto.Checkin.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Funcionario> criarFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario novoFuncionario = funcionarioService.criarFuncionario(funcionarioDTO);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

}

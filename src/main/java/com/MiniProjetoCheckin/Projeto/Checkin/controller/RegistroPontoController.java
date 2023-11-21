package com.MiniProjetoCheckin.Projeto.Checkin.controller;

import com.MiniProjetoCheckin.Projeto.Checkin.model.TipoRegistro;
import com.MiniProjetoCheckin.Projeto.Checkin.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
public class RegistroPontoController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public RegistroPontoController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/{idFuncionario}")
    public ResponseEntity<?> registrarPonto(
            @PathVariable Long idFuncionario,
            @RequestBody TipoRegistro tipoRegistro) {

        ResponseEntity<?> response = funcionarioService.registrarPonto(idFuncionario, tipoRegistro);
        return ResponseEntity.status(response.getStatusCode()).build();
    }
}

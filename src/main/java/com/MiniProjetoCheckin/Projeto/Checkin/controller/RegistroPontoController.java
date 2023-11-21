package com.MiniProjetoCheckin.Projeto.Checkin.controller;

import com.MiniProjetoCheckin.Projeto.Checkin.model.TipoRegistro;
import com.MiniProjetoCheckin.Projeto.Checkin.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Registrar ponto para um funcionário")
    @PostMapping("/{idFuncionario}")
    public ResponseEntity<?> registrarPonto(
            @Parameter(description = "ID do Funcionário", required = true)
            @PathVariable Long idFuncionario,
            @Parameter(description = "Tipo do Registro", required = true)
            @RequestBody TipoRegistro tipoRegistro) {
        ResponseEntity<?> response = funcionarioService.registrarPonto(idFuncionario, tipoRegistro);
        return ResponseEntity.status(response.getStatusCode()).build();
    }
}

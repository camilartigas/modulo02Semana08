package com.MiniProjetoCheckin.Projeto.Checkin.controller;


import com.MiniProjetoCheckin.Projeto.Checkin.dto.FuncionarioDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.dto.PontoDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.model.Funcionario;
import com.MiniProjetoCheckin.Projeto.Checkin.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> listarFuncionarios(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "12") int tamanhoPagina) {

        Page<FuncionarioDTO> funcionariosPaginados = funcionarioService.listarFuncionariosPaginados(pagina, tamanhoPagina);
        return ResponseEntity.ok(funcionariosPaginados);
    }

    @PostMapping("/{id}/registros")
    public ResponseEntity<?> adicionarPontoParaFuncionario(
            @PathVariable Long id,
            @RequestBody PontoDTO pontoDTO) {
        ResponseEntity<FuncionarioDTO> response = funcionarioService.adicionarPontoParaFuncionario(id, pontoDTO);
        return ResponseEntity.status(response.getStatusCode()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarFuncionarioPorId(@PathVariable Long id) {
        FuncionarioDTO funcionario = funcionarioService.buscarDetalhesFuncionario(id);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

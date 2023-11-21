package com.MiniProjetoCheckin.Projeto.Checkin.controller;


import com.MiniProjetoCheckin.Projeto.Checkin.dto.FuncionarioDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.dto.PontoDTO;
import com.MiniProjetoCheckin.Projeto.Checkin.model.Funcionario;
import com.MiniProjetoCheckin.Projeto.Checkin.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/funcionarios")
@Validated
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Operation(summary = "Listar funcionários paginados")
    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> listarFuncionarios(
            @Parameter(description = "Número da página (padrão = 0)")
            @RequestParam(defaultValue = "0") int pagina,
            @Parameter(description = "Tamanho da página (padrão = 12)")
            @RequestParam(defaultValue = "12") int tamanhoPagina) {
        Page<FuncionarioDTO> funcionariosPaginados = funcionarioService.listarFuncionariosPaginados(pagina, tamanhoPagina);
        return ResponseEntity.ok(funcionariosPaginados);
    }

    @Operation(summary = "Adicionar ponto para um funcionário")
    @PostMapping("/{id}/registros")
    public ResponseEntity<?> adicionarPontoParaFuncionario(
            @Parameter(description = "ID do Funcionário", required = true)
            @PathVariable Long id,
            @Parameter(description = "Objeto do Ponto a ser adicionado", required = true)
            @RequestBody PontoDTO pontoDTO) {
        ResponseEntity<FuncionarioDTO> response = funcionarioService.adicionarPontoParaFuncionario(id, pontoDTO);
        if (response.getStatusCode().isError()) {
            logger.error("Erro ao adicionar ponto para o funcionário com ID {}: {}", id, response.getStatusCodeValue());
        }
        return ResponseEntity.status(response.getStatusCode()).build();
    }

    @Operation(summary = "Buscar um funcionário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarFuncionarioPorId(
            @Parameter(description = "ID do Funcionário", required = true)
            @PathVariable Long id) {
        FuncionarioDTO funcionario = funcionarioService.buscarDetalhesFuncionario(id);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            logger.error("Funcionário não encontrado com o ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Criar um novo funcionário")
    @PostMapping
    public ResponseEntity<Funcionario> criarFuncionario(
            @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        logger.info("Criando um novo funcionário: {}", funcionarioDTO);
        Funcionario novoFuncionario = funcionarioService.criarFuncionario(funcionarioDTO);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }
}

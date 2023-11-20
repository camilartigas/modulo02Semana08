package com.MiniProjetoCheckin.Projeto.Checkin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class FuncionarioDTO {
    private Long id;
    @NotBlank
    private String nome;

    @NotBlank
    private String cargo;

    @NotNull
    private BigDecimal salario;
    private List<PontoDTO> registrosPonto;


    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public List<PontoDTO> getRegistrosPonto() {
        return registrosPonto;
    }

    public void setRegistrosPonto(List<PontoDTO> registrosPonto) {
        this.registrosPonto = registrosPonto;
    }
}

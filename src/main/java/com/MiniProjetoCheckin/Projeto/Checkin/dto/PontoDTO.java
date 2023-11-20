package com.MiniProjetoCheckin.Projeto.Checkin.dto;

import com.MiniProjetoCheckin.Projeto.Checkin.model.TipoRegistro;

import java.time.LocalDateTime;
public class PontoDTO {
    private Long id;
    private LocalDateTime horario;
    private TipoRegistro tipo;


    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public TipoRegistro getTipo() {
        return tipo;
    }

    public void setTipo(TipoRegistro tipo) {
        this.tipo = tipo;
    }
}

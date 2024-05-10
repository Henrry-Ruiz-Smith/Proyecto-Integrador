package com.crud.proyecto.prestatario.entity;

import com.crud.proyecto.usuario.entity.Usuario;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prestatario")
public class Prestatario {

    @EmbeddedId
    private PrestatarioPK prestatarioPK;

    @ManyToOne
    @JoinColumn(name = "idPrestatario", nullable = false, insertable = false, updatable = false)
    private Usuario prestatario;

    @ManyToOne
    @JoinColumn(name = "idPrestamistaCreador", nullable = false, insertable = false, updatable = false)
    private Usuario prestamista;

}
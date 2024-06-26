package com.crud.proyecto.prestamista.entity;

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
@Table(name = "prestamista")
public class Prestamista {

    @EmbeddedId
    private PrestamistaPK PrestamistaPK;

    @ManyToOne
    @JoinColumn(name = "idPrestamista", nullable = false, insertable = false, updatable = false)
    private Usuario prestamista;

    @ManyToOne
    @JoinColumn(name = "idJefePrestamistaCreador", nullable = false, insertable = false, updatable = false)
    private Usuario jefePrestamista;

}
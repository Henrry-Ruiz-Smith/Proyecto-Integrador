package com.crud.proyecto.jefeprestamista;

import com.crud.proyecto.usuario.Usuario;
import com.crud.proyecto.zona.Zona;

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
@Table(name = "jefeprestamista")
public class JefePrestamista {

    @EmbeddedId
    private JefePrestamistaPK jefePrestamistaPK;

    @ManyToOne
    @JoinColumn(name = "idJefePrestamista", nullable = false, insertable = false, updatable = false)
    private Usuario jefeprestamista;

    @ManyToOne
    @JoinColumn(name = "idInversionistaCreador", nullable = false, insertable = false, updatable = false)
    private Usuario inversionista;

	@ManyToOne
    @JoinColumn(name = "idZona")
    private Zona zona;
    

}
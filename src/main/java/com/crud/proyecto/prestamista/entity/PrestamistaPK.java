package com.crud.proyecto.prestamista.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PrestamistaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idPrestamista;
	private Long idJefePrestamistaCreador;

}
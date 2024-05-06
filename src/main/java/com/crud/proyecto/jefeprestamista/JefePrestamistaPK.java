package com.crud.proyecto.jefeprestamista;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class JefePrestamistaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idJefePrestamista;
	private Long idInversionistaCreador;

}
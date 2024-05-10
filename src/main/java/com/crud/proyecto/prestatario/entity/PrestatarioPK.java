package com.crud.proyecto.prestatario.entity;


import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PrestatarioPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idPrestatario;
	private Long idPrestamistaCreador;

}
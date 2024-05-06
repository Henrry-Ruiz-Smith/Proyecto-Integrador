package com.crud.proyecto.acceso;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class AccesoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRol;
	private int idOpcion;

}

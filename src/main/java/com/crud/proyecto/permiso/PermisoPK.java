package com.crud.proyecto.permiso;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PermisoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idUsuario;
	private int idRol;

}

package com.crud.proyecto.acceso.entity;

import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.entity.Rol;

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
@Table(name = "acceso")
public class Acceso {

	@EmbeddedId
	private AccesoPK rolHasOpcionPK;

	@ManyToOne
	@JoinColumn(name = "idRol", nullable = false, insertable = false, updatable = false)
	private Rol rol;

	@ManyToOne
	@JoinColumn(name = "idOpcion", nullable = false, insertable = false, updatable = false)
	private Opcion opcion;

}

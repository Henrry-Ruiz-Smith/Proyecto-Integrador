package com.crud.proyecto.permiso;

import com.crud.proyecto.roles.Rol;
import com.crud.proyecto.usuario.Usuario;

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
@Table(name = "permiso")
public class Permiso {

	@EmbeddedId
	private PermisoPK usuarioHasRolPk;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
	private Rol rol;

}

package com.crud.proyecto.usuario.entity;

import com.crud.proyecto.roles.entity.Rol;
import com.crud.proyecto.zona.entity.Zona;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String apellidos;
	private String contrasena;
	private String telefono;
	private String correo;
	private String username;

	private String dni;

	@ManyToOne
	@JoinColumn(name = "rol_id", referencedColumnName = "id")
	private Rol rol;

	@ManyToOne
	@JoinColumn(name = "idZona")
	private Zona zona;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellidos=");
		builder.append(apellidos);
		builder.append(", contrasena=");
		builder.append(contrasena);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", correo=");
		builder.append(correo);
		builder.append(", username=");
		builder.append(username);
		builder.append(", rol=");
		builder.append(rol);
		builder.append("]");
		return builder.toString();
	}

}

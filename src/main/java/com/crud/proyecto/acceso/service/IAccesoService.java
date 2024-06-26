package com.crud.proyecto.acceso.service;

import java.util.List;
import java.util.Optional;

import com.crud.proyecto.acceso.entity.Acceso;
import com.crud.proyecto.acceso.entity.AccesoPK;
import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.entity.Rol;


public interface IAccesoService {
	
	public abstract Rol login(Rol bean);

	public abstract List<Rol> listaRol();

	public abstract List<Opcion> traerOpcionDeRol(int idRol);

	public abstract Optional<Acceso> buscaOpcion(AccesoPK ojb);

	public abstract Acceso insertaOpcion(Acceso ojb);

	public abstract void eliminaOpcion(Acceso obj);
	
//	public abstract List<String> getRol(int idRol);
	public abstract List<Object> getObjRol(int idRol);
	
	
}

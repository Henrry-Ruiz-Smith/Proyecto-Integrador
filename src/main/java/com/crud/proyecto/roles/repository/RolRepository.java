package com.crud.proyecto.roles.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    //@Query("Select x from Rol x where x.login = :#{#rol.login} and x.password = :#{#rol.password}")
	@Query("Select x from Rol x where x.nombre = :#{#rol.nombre}")
	public abstract Rol login(@Param(value ="rol") Rol bean);
	
	@Query("Select u.opcion from Acceso u where u.rol.id = ?1")
	public abstract List<Opcion> traerOpcionDeRol(int idRol);
	
	@Query("Select u.opcion, u.rol from Acceso u where u.rol.id = ?1")
	public abstract List<Object> getObjRol(int idRol);
}

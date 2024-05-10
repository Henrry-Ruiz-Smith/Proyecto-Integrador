package com.crud.proyecto.permiso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.proyecto.permiso.entity.Permiso;
import com.crud.proyecto.permiso.entity.PermisoPK;

public interface PermisoRepository extends JpaRepository<Permiso, PermisoPK> {

}

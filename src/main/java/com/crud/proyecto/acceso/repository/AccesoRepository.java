package com.crud.proyecto.acceso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.proyecto.acceso.entity.Acceso;
import com.crud.proyecto.acceso.entity.AccesoPK;

public interface AccesoRepository extends JpaRepository<Acceso, AccesoPK> {

}

package com.crud.proyecto.model.repository;


import com.crud.proyecto.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}

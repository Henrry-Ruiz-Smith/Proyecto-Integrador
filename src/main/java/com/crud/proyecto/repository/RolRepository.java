package com.crud.proyecto.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.proyecto.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}

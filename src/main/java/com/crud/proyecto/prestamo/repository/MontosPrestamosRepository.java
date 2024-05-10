package com.crud.proyecto.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.proyecto.prestamo.entity.MontosPrestamos;

public interface MontosPrestamosRepository extends JpaRepository<MontosPrestamos, Integer> {

}
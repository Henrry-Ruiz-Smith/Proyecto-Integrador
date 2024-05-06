package com.crud.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.proyecto.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    // Puedes agregar métodos adicionales de consulta personalizada aquí si es necesario
}


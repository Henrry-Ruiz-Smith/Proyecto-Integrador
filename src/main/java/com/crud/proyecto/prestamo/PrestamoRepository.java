package com.crud.proyecto.prestamo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    // Puedes agregar métodos adicionales de consulta personalizada aquí si es necesario
}


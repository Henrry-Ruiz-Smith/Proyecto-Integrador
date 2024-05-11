package com.crud.proyecto.cuotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.proyecto.cuotas.entity.Cuota;

public interface CuotaRepository  extends JpaRepository<Cuota, Integer> {
    
}

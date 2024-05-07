package com.crud.proyecto.prestamo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.proyecto.usuario.Usuario;


@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

        List<Prestamo> findByIdPrestatario(Usuario idPrestatario);
        List<Prestamo> findByIdPrestatamista(Usuario idPrestatamista);
}


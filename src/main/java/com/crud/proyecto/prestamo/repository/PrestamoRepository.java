package com.crud.proyecto.prestamo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.proyecto.prestamo.entity.Prestamo;
import com.crud.proyecto.usuario.entity.Usuario;


@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

        List<Prestamo> findByIdPrestatario(Usuario idPrestatario);
        List<Prestamo> findByIdPrestatamista(Usuario idPrestatamista);
        List<Prestamo> findByIdPrestatarioAndEstado(Usuario idPrestatamista,String estado);

        @Query("SELECT p FROM Prestamo p WHERE p.idPrestatario.nombre LIKE  %:nombre%  AND p.fechaInicio >= :fechaIni AND  p.fechaInicio <= :fechaFi")
        List<Prestamo> buscarPrestamosPorNombrePrestatarioYRangoFechas(String nombre ,Date fechaIni,Date fechaFi );
}


package com.crud.proyecto.prestamista;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.proyecto.usuario.Usuario;

public interface PrestamistaRepository extends JpaRepository<Prestamista, PrestamistaPK> {

    @Query("SELECT p.prestamista FROM Prestamista p WHERE p.PrestamistaPK.idPrestamista IN :idsUsuarios  AND p.prestamista.zona.id = :idZona")
    List<Usuario> findUsuariosByPrestamistaIds(List<Long> idsUsuarios ,Long idZona);
}


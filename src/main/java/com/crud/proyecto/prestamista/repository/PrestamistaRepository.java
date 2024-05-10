package com.crud.proyecto.prestamista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.proyecto.prestamista.entity.Prestamista;
import com.crud.proyecto.prestamista.entity.PrestamistaPK;
import com.crud.proyecto.usuario.entity.Usuario;

public interface PrestamistaRepository extends JpaRepository<Prestamista, PrestamistaPK> {

    @Query("SELECT p.prestamista FROM Prestamista p WHERE p.PrestamistaPK.idPrestamista IN :idsUsuarios  AND p.prestamista.zona.id = :idZona AND p.PrestamistaPK.idJefePrestamistaCreador = :idJefePrestamistaCreador")
    List<Usuario> findUsuariosByPrestamistaIds(List<Long> idsUsuarios, Long idZona ,Long idJefePrestamistaCreador);
}

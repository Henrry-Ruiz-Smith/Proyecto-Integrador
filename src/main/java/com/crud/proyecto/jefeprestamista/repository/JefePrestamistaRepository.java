package com.crud.proyecto.jefeprestamista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.proyecto.jefeprestamista.entity.JefePrestamista;
import com.crud.proyecto.jefeprestamista.entity.JefePrestamistaPK;
import com.crud.proyecto.usuario.entity.Usuario;

public interface JefePrestamistaRepository extends JpaRepository<JefePrestamista, JefePrestamistaPK> {
        
    @Query("SELECT jp.jefeprestamista FROM JefePrestamista jp WHERE jp.jefePrestamistaPK.idJefePrestamista IN :idsUsuarios  AND jp.jefePrestamistaPK.idInversionistaCreador = :idInversionistaCreador")
    List<Usuario> findUsuariosByJefePrestamistaIds(List<Long> idsUsuarios ,Long idInversionistaCreador);
}

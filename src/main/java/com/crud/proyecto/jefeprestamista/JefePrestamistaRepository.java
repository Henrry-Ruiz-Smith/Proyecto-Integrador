package com.crud.proyecto.jefeprestamista;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.proyecto.usuario.Usuario;

public interface JefePrestamistaRepository extends JpaRepository<JefePrestamista, JefePrestamistaPK> {
        
    @Query("SELECT jp.jefeprestamista FROM JefePrestamista jp WHERE jp.jefePrestamistaPK.idJefePrestamista IN :idsUsuarios  AND jp.jefeprestamista.zona.id = :idZona")
    List<Usuario> findUsuariosByJefePrestamistaIds(List<Long> idsUsuarios ,Long idZona);
}

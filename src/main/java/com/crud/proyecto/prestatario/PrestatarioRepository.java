package com.crud.proyecto.prestatario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.proyecto.usuario.Usuario;

public interface PrestatarioRepository extends JpaRepository<Prestatario, PrestatarioPK> {

    @Query("SELECT p.prestatario FROM Prestatario p WHERE p.prestatarioPK.idPrestatario IN :idsUsuarios  AND p.prestatario.zona.id = :idZona AND p.prestatarioPK.idPrestamistaCreador = :idPrestamistaCreador")
    List<Usuario> findUsuariosByPrestatarioIds(List<Long> idsUsuarios, Long idZona, Long idPrestamistaCreador);

    @Query("SELECT p FROM Prestatario p WHERE  p.prestatarioPK.idPrestatario = :idPrestatario")
    List<Prestatario> buscarPorPrestatario(Long idPrestatario);

}

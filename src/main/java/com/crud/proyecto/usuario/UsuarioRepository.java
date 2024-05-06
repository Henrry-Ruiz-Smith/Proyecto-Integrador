package com.crud.proyecto.usuario;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
	Usuario findByUsernameAndContrasena(String correo, String contrasena);
    
    @Query(value = "SELECT * FROM usuarios WHERE nombre LIKE %:textBusqueda% OR apellidos LIKE %:textBusqueda%", nativeQuery = true)
    List<Usuario> buscarUsuarioNombreYApellido(@Param("textBusqueda") String textBusqueda);
    
    @Query(value = "SELECT u.* FROM usuarios u INNER JOIN roles r ON u.rol_id = r.id WHERE r.nombre = :nombreRol", nativeQuery = true)
    List<Usuario> listarUsuarioXRol (@Param("nombreRol") String nombreRol);
}

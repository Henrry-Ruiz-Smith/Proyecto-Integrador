package com.crud.proyecto.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.Rol;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsernameAndContrasena(String correo, String contrasena);

    @Query(value = "SELECT * FROM usuarios WHERE nombre LIKE %:textBusqueda% OR apellidos LIKE %:textBusqueda%", nativeQuery = true)
    List<Usuario> buscarUsuarioNombreYApellido(@Param("textBusqueda") String textBusqueda);

    @Query(value = "SELECT u.* FROM usuarios u INNER JOIN roles r ON u.rol_id = r.id WHERE r.nombre = :nombreRol", nativeQuery = true)
    List<Usuario> listarUsuarioXRol(@Param("nombreRol") String nombreRol);

    @Query(value = "SELECT u.* FROM usuarios u  WHERE (u.nombre LIKE %:textBusqueda% OR u.apellidos LIKE %:textBusqueda%) and u.rol_id = :idRol", nativeQuery = true)
    List<Usuario> buscarUsuarioNombreYApellidoXRol(@Param("textBusqueda") String textBusqueda,
            @Param("idRol") int idRol);

    @Query("Select p from Opcion p, RolHasOpcion pr, Rol r, UsuarioHasRol u"
            + " where  p.idOpcion = pr.opcion.idOpcion "
            + " and pr.rol.idRol = r.idRol and r.idRol = u.rol.idRol and u.usuario.idUsuario = :var_idUsuario")
    public abstract List<Opcion> traerEnlacesDeUsuario(@Param("var_idUsuario") Long idUsuario);

    @Query("Select r from Rol r, UsuarioHasRol u"
            + " where r.idRol = u.rol.idRol and u.usuario.idUsuario = :var_idUsuario")
    public abstract List<Rol> traerRolesDeUsuario(@Param("var_idUsuario") Long idUsuario);
}

package com.crud.proyecto.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.entity.Rol;
import com.crud.proyecto.usuario.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        Usuario findByUsernameAndContrasena(String correo, String contrasena);

        List<Usuario> findByCorreo(String email);

        List<Usuario> findByUsername(String userName);

        List<Usuario> findByDni(String dni);

        @Query(value = "SELECT * FROM usuarios WHERE nombre LIKE %:textBusqueda% OR apellidos LIKE %:textBusqueda% AND activo = 1", nativeQuery = true)
        List<Usuario> buscarUsuarioNombreYApellido(@Param("textBusqueda") String textBusqueda);

        @Query(value = "SELECT u.* FROM usuarios u INNER JOIN roles r ON u.rol_id = r.id WHERE r.nombre = :nombreRol AND u.activo = 1", nativeQuery = true)
        List<Usuario> listarUsuarioXRol(@Param("nombreRol") String nombreRol);

        @Query(value = "SELECT u.* FROM usuarios u  WHERE (u.nombre LIKE %:textBusqueda% OR u.apellidos LIKE %:textBusqueda%) and u.rol_id = :idRol AND u.activo = 1", nativeQuery = true)
        List<Usuario> buscarUsuarioNombreYApellidoXRol(@Param("textBusqueda") String textBusqueda,
                        @Param("idRol") Long idRol);

        @Query("Select p from Opcion p, Acceso pr, Rol r, Permiso u"
                        + " where  p.idOpcion = pr.opcion.idOpcion "
                        + " and pr.rol.id = r.id"
                        + " and r.id=u.rol.id and u.usuario.id=:var_idUsuario")

        public abstract List<Opcion> traerEnlacesDeUsuario(@Param("var_idUsuario") Long idUsuario);

        @Query("Select r from Rol r, Permiso u"
                        + " where r.id = u.rol.id and u.usuario.id = :var_idUsuario")
        public abstract List<Rol> traerRolesDeUsuario(@Param("var_idUsuario") Long idUsuario);

}

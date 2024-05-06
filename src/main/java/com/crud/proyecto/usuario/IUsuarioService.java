package com.crud.proyecto.usuario;

import java.util.List;

import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.Rol;
import com.crud.proyecto.zona.Zona;

public interface IUsuarioService {

    List<Usuario> findAll();

    List<Rol> listarRoles();

    Usuario findById(Long id);

    Usuario save(Usuario usuario);

    Usuario update(Long id, Usuario usuario);

    void delete(Long id);

    Usuario iniciarSesion(String username, String contrasena);

    List<Usuario> buscarUsuarioNombreXApellido(String textoBuscar);

    List<Usuario> buscarUsuarioNombreYApellidoXRol(String textoBuscar, Long idRol);

    List<Usuario> listarUsuarios(String nombreRol);

    List<Usuario> listarUsuarios(int idRol);

    void registrar(Usuario usuario);

    List<Opcion> traerEnlacesDeUsuario(Long idUsuario);

    List<Rol> traerRolesDeUsuario(Long idUsuario);

   Usuario registrarUsuario(Usuario bean, Long idRol ,Usuario usarioSesion,Zona zona);
}

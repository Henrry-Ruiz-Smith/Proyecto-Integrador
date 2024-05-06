package com.crud.proyecto.usuario;

import java.util.List;

import com.crud.proyecto.roles.Rol;

public interface IUsuarioService {

    List<Usuario> findAll();

    List<Rol> listarRoles();

    Usuario findById(Long id);

    Usuario save(Usuario usuario);

    Usuario update(Long id, Usuario usuario);

    void delete(Long id);

    Usuario iniciarSesion(String username, String contrasena);

    List<Usuario> buscarUsuarioNombreXApellido(String textoBuscar);

    List<Usuario> listarUsuarios(String nombreRol);

    List<Usuario> listarUsuariosPorIdRol(int idRol);

    void registrar(Usuario usuario);

}

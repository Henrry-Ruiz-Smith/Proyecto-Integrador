package com.crud.proyecto.IService;

import java.util.List;

import com.crud.proyecto.entity.Rol;
import com.crud.proyecto.entity.Usuario;



public interface UsuarioService {

    List<Usuario> findAll();
    
    List<Rol> listarRoles();

    Usuario findById(Long id);

    Usuario save(Usuario usuario);

    Usuario update(Long id, Usuario usuario);

    void delete(Long id);
    
    Usuario iniciarSesion(String username, String contrasena);
    
    List<Usuario> buscarUsuarioNombreXApellido(String textoBuscar);
    
    List<Usuario> listarUsuarios (String nombreRol);

    void registrar(Usuario usuario);
    
}

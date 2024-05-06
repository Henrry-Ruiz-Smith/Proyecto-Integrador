package com.crud.proyecto.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.Rol;
import com.crud.proyecto.roles.RolRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElse(null);
        if (existingUsuario != null) {
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellidos(usuario.getApellidos());
            existingUsuario.setContrasena(usuario.getContrasena());
            existingUsuario.setTelefono(usuario.getTelefono());
            existingUsuario.setCorreo(usuario.getCorreo());
            existingUsuario.setRol(usuario.getRol());
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario iniciarSesion(String username, String contrasena) {
        return usuarioRepository.findByUsernameAndContrasena(username, contrasena);
    }

    @Override
    public List<Usuario> buscarUsuarioNombreXApellido(String textoBuscar) {
        return usuarioRepository.buscarUsuarioNombreYApellido(textoBuscar);
    }

    @Override
    public List<Rol> listarRoles() {

        return rolRepository.findAll();
    }

    @Override
    public List<Usuario> listarUsuarios(String nombreRol) {

        Map<String, String> roles = new HashMap<>();

        roles.put("admin", "Admin");
        roles.put("Inversionista", "Jefe de Prestamistas");
        roles.put("Jefe de Prestamistas", "Prestamista");
        roles.put("Prestamista", "Prestatario");

        String rolSiguiente = roles.get(nombreRol);
        if (rolSiguiente != null) {
            return usuarioRepository.listarUsuarioXRol(rolSiguiente);
        } else {
            throw new IllegalArgumentException("Rol desconocido: " + nombreRol);
        }
    }

    @Override
    public void registrar(Usuario usuario) {
        throw new UnsupportedOperationException("Unimplemented method 'registrar'");
    }

    @Override
    public List<Usuario> listarUsuarios(int idRol) {
        throw new UnsupportedOperationException("Unimplemented method 'listarUsuariosPorIdRol'");
    }

    @Override
    public List<Usuario> buscarUsuarioNombreYApellidoXRol(String textoBuscar, int idRol) {

        return usuarioRepository.buscarUsuarioNombreYApellidoXRol(textoBuscar, idRol);
    }

    @Override
    public List<Opcion> traerEnlacesDeUsuario(Long idUsuario) {
        return usuarioRepository.traerEnlacesDeUsuario(idUsuario);
    }

    @Override
    public List<Rol> traerRolesDeUsuario(Long idUsuario) {
        return usuarioRepository.traerRolesDeUsuario(idUsuario);
    }

}

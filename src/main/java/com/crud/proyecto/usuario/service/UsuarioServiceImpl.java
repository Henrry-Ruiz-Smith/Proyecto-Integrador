package com.crud.proyecto.usuario.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.jefeprestamista.entity.JefePrestamista;
import com.crud.proyecto.jefeprestamista.entity.JefePrestamistaPK;
import com.crud.proyecto.jefeprestamista.repository.JefePrestamistaRepository;
import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.permiso.entity.Permiso;
import com.crud.proyecto.permiso.entity.PermisoPK;
import com.crud.proyecto.permiso.repository.PermisoRepository;
import com.crud.proyecto.prestamista.entity.Prestamista;
import com.crud.proyecto.prestamista.entity.PrestamistaPK;
import com.crud.proyecto.prestamista.repository.PrestamistaRepository;
import com.crud.proyecto.prestatario.entity.Prestatario;
import com.crud.proyecto.prestatario.entity.PrestatarioPK;
import com.crud.proyecto.prestatario.repository.PrestatarioRepository;
import com.crud.proyecto.roles.entity.Rol;
import com.crud.proyecto.roles.repository.RolRepository;
import com.crud.proyecto.usuario.entity.Usuario;
import com.crud.proyecto.usuario.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private JefePrestamistaRepository jefePrestamistaRepository;
    @Autowired
    private PrestamistaRepository prestamistaRepository;
    @Autowired
    private PrestatarioRepository prestatarioRepository;

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
        roles.put("Prestamista", "Prestamista");

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
    public List<Usuario> buscarUsuarioNombreYApellidoXRol(String textoBuscar, Long idRol, Usuario idUsuarioCreador) {
        // Buscar usuarios por nombre, apellido y rol
        List<Usuario> usuarios = usuarioRepository.buscarUsuarioNombreYApellidoXRol(textoBuscar, idRol);

        // Obtener los IDs de los usuarios encontrados
        List<Long> idsUsuarios = usuarios.stream().map(Usuario::getId).collect(Collectors.toList());
        System.out.println("ID USUARIOS: " + idsUsuarios);
        int idRolUsuarioEnSesion = (int) idUsuarioCreador.getRol().getId().longValue();
        List<Usuario> usuariosAsociados = new ArrayList<>();
        switch (idRolUsuarioEnSesion) {
            case 2:
                // Obtener los usuarios JefePrestamista asociados a los Inversionistas
                return jefePrestamistaRepository.findUsuariosByJefePrestamistaIds(
                        idsUsuarios, idUsuarioCreador.getId());
            case 3:
                // Obtener los usuarios Prestamistas asociados a los JefePrestamista
                return prestamistaRepository.findUsuariosByPrestamistaIds(
                        idsUsuarios,
                        idUsuarioCreador.getZona().getId(), idUsuarioCreador.getId());
            case 4:
                // Obtener los usuarios Prestatarios asociados a los Prestamistas
                return prestatarioRepository.findUsuariosByPrestatarioIds(
                        idsUsuarios,
                        idUsuarioCreador.getZona().getId(), idUsuarioCreador.getId());
            default:
                return usuariosAsociados;
        }
    }

    @Override
    public List<Opcion> traerEnlacesDeUsuario(Long idUsuario) {
        return usuarioRepository.traerEnlacesDeUsuario(idUsuario);
    }

    @Override
    public List<Rol> traerRolesDeUsuario(Long idUsuario) {
        return usuarioRepository.traerRolesDeUsuario(idUsuario);
    }

    @Override
    public Usuario registrarUsuario(Usuario bean, Long idRol, Usuario usuarioSesion) {
        // Guardar el Usuario
        Usuario objSalida = usuarioRepository.save(bean);
        asignarRolAUsuario(objSalida.getId(), idRol);
        crearEntidadRelacionada(objSalida.getId(), idRol, usuarioSesion.getId());

        return objSalida;
    }

    @Override
    public List<Usuario> validarEmail(String email) {
        return usuarioRepository.findByCorreo(email);
    }

    @Override
    public List<Usuario> validarDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    @Override
    public List<Usuario> validarUserName(String username) {
        return usuarioRepository.findByUsername(username);
    }

    private void asignarRolAUsuario(Long usuarioId, Long rolId) {
        PermisoPK hasRolPK = new PermisoPK();
        hasRolPK.setIdUsuario(usuarioId);
        hasRolPK.setIdRol(rolId);
        Permiso usuarioHasRol = new Permiso();
        usuarioHasRol.setUsuarioHasRolPk(hasRolPK);
        permisoRepository.save(usuarioHasRol);
    }

    private void crearEntidadRelacionada(Long usuarioId, Long rolId, Long creadorId) {
        switch (rolId.intValue()) {
            case 3:
                JefePrestamistaPK jpPK = new JefePrestamistaPK();
                jpPK.setIdJefePrestamista(usuarioId);
                jpPK.setIdInversionistaCreador(usuarioId);

                JefePrestamista jefePrestamista = new JefePrestamista();
                jefePrestamista.setJefePrestamistaPK(jpPK);
                jefePrestamistaRepository.save(jefePrestamista);
                break;
            case 4:
                PrestamistaPK presPK = new PrestamistaPK();
                presPK.setIdPrestamista(usuarioId);
                presPK.setIdJefePrestamistaCreador(usuarioId);

                Prestamista prestamista = new Prestamista();
                prestamista.setPrestamistaPK(presPK);
                prestamistaRepository.save(prestamista);
                break;
            case 5:
                PrestatarioPK prestPK = new PrestatarioPK();
                prestPK.setIdPrestatario(usuarioId);
                prestPK.setIdPrestamistaCreador(usuarioId);

                Prestatario prestatario = new Prestatario();
                prestatario.setPrestatarioPK(prestPK);
                prestatarioRepository.save(prestatario);
                break;
            default:
                break;
        }
    }

}

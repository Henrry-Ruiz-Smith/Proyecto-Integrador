package com.crud.proyecto.usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.proyecto.jefeprestamista.JefePrestamista;
import com.crud.proyecto.jefeprestamista.JefePrestamistaPK;
import com.crud.proyecto.jefeprestamista.JefePrestamistaRepository;
import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.permiso.Permiso;
import com.crud.proyecto.permiso.PermisoPK;
import com.crud.proyecto.permiso.PermisoRepository;
import com.crud.proyecto.prestamista.Prestamista;
import com.crud.proyecto.prestamista.PrestamistaPK;
import com.crud.proyecto.prestamista.PrestamistaRepository;
import com.crud.proyecto.prestatario.Prestatario;
import com.crud.proyecto.prestatario.PrestatarioPK;
import com.crud.proyecto.prestatario.PrestatarioRepository;
import com.crud.proyecto.roles.Rol;
import com.crud.proyecto.roles.RolRepository;

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
                        idsUsuarios,
                        idUsuarioCreador.getZona().getId(), idUsuarioCreador.getId());
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
    public Usuario registrarUsuario(Usuario bean, Long idRol, Usuario usarioSesion) {
        // Guardar el Usuario
        Usuario objSalida = usuarioRepository.save(bean);
        // Configurar UsuarioHasRolPK
        PermisoPK hasRolPK = new PermisoPK();
        hasRolPK.setIdUsuario(objSalida.getId());
        hasRolPK.setIdRol(idRol);

        // Crear y guardar UsuarioHasRol
        Permiso usuarioHasRol = new Permiso();
        usuarioHasRol.setUsuarioHasRolPk(hasRolPK);
        permisoRepository.save(usuarioHasRol);

        // Convertir el valor long a int (teniendo en cuenta la posibilidad de pérdida
        // de información o desbordamiento)
        long idRola = idRol.longValue();
        int idRolInt = (int) idRola;

        switch (idRolInt) {
            case 3:
                JefePrestamistaPK jpPK = new JefePrestamistaPK();
                jpPK.setIdJefePrestamista(objSalida.getId());
                jpPK.setIdInversionistaCreador(usarioSesion.getId());

                JefePrestamista jefePrestamista = new JefePrestamista();
                jefePrestamista.setJefePrestamistaPK(jpPK);
                jefePrestamistaRepository.save(jefePrestamista);
                break;
            case 4:
                PrestamistaPK presPK = new PrestamistaPK();
                presPK.setIdPrestamista(objSalida.getId());
                presPK.setIdJefePrestamistaCreador(usarioSesion.getId());

                Prestamista prestamista = new Prestamista();
                prestamista.setPrestamistaPK(presPK);
                prestamistaRepository.save(prestamista);
                break;
            case 5:
                PrestatarioPK prestPK = new PrestatarioPK();
                prestPK.setIdPrestatario(objSalida.getId());
                prestPK.setIdPrestamistaCreador(usarioSesion.getId());

                Prestatario prestatario = new Prestatario();
                prestatario.setPrestatarioPK(prestPK);
                prestatarioRepository.save(prestatario);
                break;
            default:
                break;
        }

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

}

package com.crud.proyecto.jefeprestamista.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.proyecto.jefeprestamista.repository.JefePrestamistaRepository;
import com.crud.proyecto.permiso.repository.PermisoRepository;
import com.crud.proyecto.roles.entity.Rol;
import com.crud.proyecto.usuario.entity.Usuario;
import com.crud.proyecto.usuario.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JefePrestamistaController {
    private final Long ROL_JEFE_PRESTAMISTA = 3L;
    private final String msg_error = "msg_error";
    private final String msg_ok = "msg_ok";
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private JefePrestamistaRepository jefePrestamistaRepository;
    @Autowired
    private PermisoRepository permisoRepository;

    @GetMapping("/buscarJefePrestamista")
    @ResponseBody
    // Busqueda por Datos Completos
    public Map<String, Object> listaComplejo(@RequestParam String nombresApellidos, HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("usuario");
        try {
            List<Usuario> usuarioJefePrestamista = usuarioService
                    .buscarUsuarioNombreYApellidoXRol(nombresApellidos, ROL_JEFE_PRESTAMISTA, user);

            return usuarioJefePrestamista.isEmpty()
                    ? Collections.singletonMap("mensaje", "No hay coincidencias")
                    : Collections.singletonMap("lista", usuarioJefePrestamista);

        } catch (Exception e) {
            return Collections.singletonMap("error", "Error de Servidor");
        }
    }

    @PostMapping("/insertJefePrestamista")
    @ResponseBody
    public Map<?, ?> insertJefePrestamista(Usuario jp, HttpSession session) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        if (Validacioness.campoVacio(jp.getNombre())) {
            map.put(msg_error, "nombre vacío");
            return map;
        }
        if (Validacioness.campoVacio(jp.getApellidos())) {
            map.put(msg_error, " apellidos vacío");
            return map;
        }

        if (Validacioness.campoVacio(jp.getDni())) {
            map.put(msg_error, "Dni vacío");
            return map;
        }

        if (!Validacioness.validarDni(jp.getDni())) {
            map.put(msg_error, "Dni Incorrecto 8 digitos");
            return map;
        }
        List<Usuario> listEmail = usuarioService.validarDni(jp.getDni());
        if (listEmail.size() > 0) {
            map.put(msg_error, "El dni ya pertenece a otro usuario");
            return map;
        }

        if (Validacioness.campoVacio(jp.getTelefono())) {
            map.put(msg_error, "telefono vacío");
            return map;
        }

        if (Validacioness.campoVacio(jp.getCorreo())) {
            map.put(msg_error, " E-mail vacío");
            return map;
        }

        if (!Validacioness.validarEmail(jp.getCorreo())) {
            map.put(msg_error, "Email Incorrecto");
            return map;
        }
        if (usuarioService.validarEmail(jp.getCorreo()).size() > 0) {
            map.put(msg_error, "El correo ya pertenece a otro usuario");
            return map;
        }

        if (jp.getZona().getId() == null) {
            map.put(msg_error, "Escoge ZonA ");
            return map;
        }
        if (Validacioness.campoVacio(jp.getUsername())) {
            map.put(msg_error, "Usuario Nombre Vacio");
            return map;
        }

        if (usuarioService.validarUserName(jp.getUsername()).size() > 0) {
            map.put(msg_error, "El nombre de usuario ya pertenece a otro usuario");
            return map;
        }

        if (Validacioness.campoVacio(jp.getContrasena())) {
            map.put(msg_error, "Contrasena Vacio");
            return map;
        }

        Usuario user = (Usuario) session.getAttribute("usuario");

        Rol rolJPrestamista = new Rol();
        rolJPrestamista.setId(ROL_JEFE_PRESTAMISTA);
        jp.setRol(rolJPrestamista);
        jp.setActivo(1);
        Usuario salida = usuarioService.registrarUsuario(jp, ROL_JEFE_PRESTAMISTA, user);

        if (salida == null) {
            map.put(msg_error, "ERROR AL REGISTRAR");
        } else {

            map.put(msg_ok, "Tu registro fue exitoso!");
        }
        return map;
    }

    @PostMapping("/editarJefePrestamista")
    @ResponseBody
    public Map<?, ?> editarJefePrestamista(@RequestParam String id, @RequestParam String nom,
            @RequestParam String ape,
            @RequestParam String dni,
            @RequestParam String tel,
            @RequestParam String cor,
            @RequestParam String use,
            @RequestParam String zid,
            @RequestParam String pass,
             HttpSession session) {
        Long numeroComoLong = Long.parseLong(id);
        Usuario jp = usuarioService.findById(numeroComoLong);

        HashMap<String, Object> map = new HashMap<String, Object>();
        if (Validacioness.campoVacio(nom)) {
            map.put(msg_error, "nombre vacío");
            return map;
        }
        jp.setNombre(nom);

        if (Validacioness.campoVacio(ape)) {
            map.put(msg_error, " apellidos vacío");
            return map;
        }
        jp.setApellidos(ape);

        if (Validacioness.campoVacio(dni)) {
            map.put(msg_error, "Dni vacío");
            return map;
        }

        if (!Validacioness.validarDni(dni)) {
            map.put(msg_error, "Dni Incorrecto 8 digitos");
            return map;
        }

        if (!jp.getDni().equalsIgnoreCase(dni)) {
            List<Usuario> listEmail = usuarioService.validarDni(dni);
            if (listEmail.size() > 0) {
                map.put(msg_error, "El dni ya pertenece a otro usuario");
                return map;
            }
        }
        jp.setDni(dni);

        if (Validacioness.campoVacio(tel)) {
            map.put(msg_error, "telefono vacío");
            return map;
        }
        jp.setTelefono(tel);
        if (Validacioness.campoVacio(cor)) {
            map.put(msg_error, " E-mail vacío");
            return map;
        }

        if (!Validacioness.validarEmail(cor)) {
            map.put(msg_error, "Email Incorrecto");
            return map;
        }
        if (!jp.getCorreo().equalsIgnoreCase(cor)) {
            if (usuarioService.validarEmail(cor).size() > 0) {
                map.put(msg_error, "El correo ya pertenece a otro usuario");
                return map;
            }
        }
        jp.setCorreo(cor);
        if (zid.equals("")) {
            map.put(msg_error, "Escoge ZonA ");
            return map;
        }
        if (Validacioness.campoVacio(use)) {
            map.put(msg_error, "Usuario Nombre Vacio");
            return map;
        }

        if (!jp.getUsername().equalsIgnoreCase(use)) {
            if (usuarioService.validarUserName(use).size() > 0) {
                map.put(msg_error, "El nombre de usuario ya pertenece a otro usuario");
                return map;
            }
        }
        jp.setUsername(use);
        
        if (Validacioness.campoVacio(pass)) {
            map.put(msg_error, "Contrasena Vacio");
            return map;
        }
        

        Usuario user = (Usuario) session.getAttribute("usuario");

        Rol rolJPrestamista = new Rol();
        rolJPrestamista.setId(ROL_JEFE_PRESTAMISTA);
        jp.setRol(rolJPrestamista);
        jp.setActivo(1);
        Usuario salida = usuarioService.registrarUsuario(jp, ROL_JEFE_PRESTAMISTA, user);

        if (salida == null) {
            map.put(msg_error, "ERROR AL REGISTRAR");
        } else {

            map.put(msg_ok, "Actualización exitosa!");
        }
        return map;
    }

    @PutMapping("/eliminacionJefePrestamista")
    @ResponseBody
    public Map<?, ?> deleteJefePrestamista(Usuario jp, HttpSession session) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        // Obtener el usuario que deseas actualizar por su ID
        Usuario usuario = usuarioService.findById(jp.getId());

        // Cambiar el campo 'activo'
        usuario.setActivo(0); // nuevoValor puede ser true o false dependiendo de lo que desees establecer

        // Guardar el usuario actualizado en la base de datos
        usuarioService.save(usuario);
        return map;

    }
}

class Validacioness {

    private static final Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public static boolean campoVacio(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean validarEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarDni(String str) {
        try {
            // Verificar si la cadena se puede convertir a un número entero
            Integer.parseInt(str);

            // Verificar si la cadena tiene exactamente 8 caracteres
            if (str.length() != 8) {
                return false;
            }

            // Si se cumplieron ambas condiciones, retornar true
            return true;
        } catch (NumberFormatException e) {
            // Si ocurrió una excepción al intentar convertir la cadena a entero, retornar
            // false
            return false;
        }
    }

}

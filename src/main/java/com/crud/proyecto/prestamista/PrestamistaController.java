package com.crud.proyecto.prestamista;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.proyecto.roles.Rol;
import com.crud.proyecto.usuario.IUsuarioService;
import com.crud.proyecto.usuario.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class PrestamistaController {
    private final Long ROL_PRESTAMISTA = 4L;
    private final String msg_error = "msg_error";
    private final String msg_ok = "msg_ok";
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/buscarPrestamista")
    @ResponseBody
    // Busqueda por Datos Completos
    public Map<String, Object> listaComplejo(@RequestParam String nombresApellidos, HttpSession session) {

        try {
            Usuario user = (Usuario) session.getAttribute("usuario");

            List<Usuario> usuarioPrestamista = usuarioService
                    .buscarUsuarioNombreYApellidoXRol(nombresApellidos, ROL_PRESTAMISTA, user);

            return usuarioPrestamista.isEmpty()
                    ? Collections.singletonMap("mensaje", "No hay coincidencias")
                    : Collections.singletonMap("lista", usuarioPrestamista);

        } catch (Exception e) {
            return Collections.singletonMap("error", "Error de Servidor");
        }
    }

    // Registro de de Prestamita
    @PostMapping("/insertPrestamista")
    @ResponseBody
    public Map<?, ?> insertPrestamista(Usuario jp, HttpSession session) {

        Usuario user = (Usuario) session.getAttribute("usuario");

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
        jp.setZona(user.getZona());
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

        Rol rolJPrestamista = new Rol();
        rolJPrestamista.setId(ROL_PRESTAMISTA);
        jp.setRol(rolJPrestamista);

        Usuario salida = usuarioService.registrarUsuario(jp, ROL_PRESTAMISTA, user);

        if (salida == null) {
            map.put(msg_error, "ERROR AL REGISTRAR");
        } else {

            map.put(msg_ok, "Tu registro fue exitoso!");
        }
        return map;
    }

    @DeleteMapping("/eliminacionPrestamista")
    @ResponseBody
    public Map<?, ?> deletePrestamista(Usuario jp, HttpSession session) {

        HashMap<String, Object> map = new HashMap<String, Object>();

        try {
            usuarioService.delete(jp.getId());
            map.put(msg_ok, "El  prestamista fue eliminado exitosamente.");
        } catch (Exception e) {
            map.put(msg_error, "Ocurrió un error al eliminar el  prestamista.");
            e.printStackTrace(); // Manejo de errores, puedes personalizar esto según tus necesidades
        }
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

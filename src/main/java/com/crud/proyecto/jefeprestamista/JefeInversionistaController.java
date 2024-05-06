package com.crud.proyecto.jefeprestamista;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.proyecto.roles.Rol;
import com.crud.proyecto.usuario.IUsuarioService;
import com.crud.proyecto.usuario.Usuario;
import com.crud.proyecto.zona.Zona;

import jakarta.servlet.http.HttpSession;

@Controller
public class JefeInversionistaController {
    private final Long ROL_JEFE_PRESTAMISTA = 3L;
    private final int ROL_PRESTAMISTA = 4;
    private final String msg_error = "msg_error";
    private final String msg_ok = "msg_ok";
    @Autowired
    private IUsuarioService usuarioService;
    

    @GetMapping("/buscarJefePrestamista")
    @ResponseBody
    // Busqueda por Datos Completos
    public Map<String, Object> listaComplejo(@RequestParam String nombresApellidos) {

        try {
            List<Usuario> usuarioJefePrestamista = usuarioService
                    .buscarUsuarioNombreYApellidoXRol(nombresApellidos, ROL_JEFE_PRESTAMISTA);

            return usuarioJefePrestamista.isEmpty()
                    ? Collections.singletonMap("mensaje", "No hay coincidencias")
                    : Collections.singletonMap("lista", usuarioJefePrestamista);

        } catch (Exception e) {
            return Collections.singletonMap("error", "Error de Servidor");
        }
    }

    // Registro de Jefe de Prestamita
    @PostMapping("/RegistrarJefePrestamista")
    @ResponseBody
    public Map<?, ?> insertJefePrestamista(Usuario jp, HttpSession session ,Zona zona) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        if (Validacioness.campoVacio(jp.getNombre())) {
            map.put(msg_error, "nombre vacío");
            return map;
        }
        if (Validacioness.campoVacio(jp.getApellidos())) {
            map.put(msg_error, " apellidos vacío");
            return map;
        }

        if (Validacioness.campoVacio(String.valueOf(jp.getDni()))) {
            map.put(msg_error, "Dni vacío");
            return map;
        }

        if (!Validacioness.validarDni(String.valueOf(jp.getDni()))) {
            map.put(msg_error, "Dni Incorrecto 8 digitos");
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

        
        if (zona.getId() == null) {
            map.put(msg_error, "Escoge ZonA ");
            return map;
        }

        Usuario user = (Usuario) session.getAttribute("usuario");
        jp.setUsername(jp.getNombre().substring(0, 3).replace(" ", "").toLowerCase()
                + "." + jp.getApellidos().substring(0, 3).replace(" ", "").toLowerCase()
                + String.valueOf(jp.getDni()).charAt(0)
                + "JPMA");
        jp.setContrasena(String.valueOf(jp.getDni()));

        Rol rolJPrestamista = new Rol();
        rolJPrestamista.setId(ROL_JEFE_PRESTAMISTA);
        jp.setRol(rolJPrestamista);

        Usuario salida = usuarioService.registrarUsuario(jp, ROL_JEFE_PRESTAMISTA, user , zona);

        if (salida == null) {
            map.put(msg_error, "ERROR AL REGISTRAR");
        } else {

            map.put(msg_ok, "Tu registro fue exitoso!");
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

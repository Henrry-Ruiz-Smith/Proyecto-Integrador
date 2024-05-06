package com.crud.proyecto.jefeprestamista;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.proyecto.roles.IRolService;
import com.crud.proyecto.usuario.IUsuarioService;
import com.crud.proyecto.usuario.Usuario;

@Controller
public class JefeInversionistaController {
    private final int ROL_JEFE_PRESTAMISTA = 3;
    private final int ROL_PRESTAMISTA = 4;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IRolService iRolService;

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

}

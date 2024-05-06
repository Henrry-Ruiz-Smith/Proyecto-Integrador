package com.crud.proyecto.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.proyecto.opcion.Opcion;
import com.crud.proyecto.roles.Rol;
import com.crud.proyecto.usuario.IUsuarioService;
import com.crud.proyecto.usuario.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("usuario");
        session.invalidate();
        return "redirect:/?logoutSuccess=true";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam("username") String username,
            @RequestParam("contrasena") String contrasena, HttpServletRequest request) {
        Usuario usuarioLogueado = usuarioService.iniciarSesion(username, contrasena);
        if (usuarioLogueado != null) {

            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuarioLogueado);
            List<Rol> roles = usuarioService.traerRolesDeUsuario(usuarioLogueado.getId());
            List<Opcion> menus = usuarioService.traerEnlacesDeUsuario(usuarioLogueado.getId());
            session.setAttribute("objRoles", roles);
            switch (Integer.parseInt(usuarioLogueado.getRol().getId().toString())) {
                case 1:
                    return "redirect:/" + menus.get(0).getRuta();
                // INVERSIONISTA
                case 2:
                    return "redirect:/" + menus.get(0).getRuta();
                // JEFE PRESTAMISTA
                case 3:
                    return "redirect:/" + menus.get(0).getRuta();
                // PRESTAMISTA
                case 4:
                    return "redirect:/" + menus.get(0).getRuta();
                // PRESTAMISTA
                case 5:
                    return "redirect:/" + menus.get(0).getRuta();

                default:
                    return "redirect:/usuarios/principal";
            }

        } else {
            return "redirect:/?error";
        }
    }

}

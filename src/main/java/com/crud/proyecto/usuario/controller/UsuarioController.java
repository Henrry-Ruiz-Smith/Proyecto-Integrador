package com.crud.proyecto.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.proyecto.roles.entity.Rol;
import com.crud.proyecto.usuario.entity.Usuario;
import com.crud.proyecto.usuario.service.IUsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/principal")
    public String mostrarUsuarios(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null) {
            List<Usuario> usuarios = usuarioService.listarUsuarios(usuario.getRol().getNombre());
            model.addAttribute("usuarios", usuarios);
        }

        return "index";
    }

    @GetMapping("/buscarUsuario")
    public String buscarUsuario(@RequestParam("query") String query, Model model) {
        List<Usuario> usuarios = usuarioService.buscarUsuarioNombreXApellido(query);
        model.addAttribute("usuarios", usuarios);
        return "index";
    }

    @GetMapping("/formularioUsuario")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("usuario", new Usuario());
        List<Rol> roles = usuarioService.listarRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("tituloAccion", "Nuevo Usuario");
        model.addAttribute("botonAccion", "Guardar Usuario");
        return "crear";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        if (usuario != null && usuario.getId() != null && usuario.getId() != 0) {
            usuarioService.update(usuario.getId(), usuario);
        } else {
            usuarioService.save(usuario);
        }
        return "redirect:/usuarios/principal";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        List<Rol> roles = usuarioService.listarRoles();

        model.addAttribute("roles", roles);
        model.addAttribute("tituloAccion", "Editar Usuario");
        model.addAttribute("botonAccion", "Actualizar Usuario");

        if (usuario != null) {
            System.out.println("usuarios a edirtar " + usuario);
            model.addAttribute("usuario", usuario);
            return "crear";
        } else {
            return "redirect:/usuarios/principal";
        }
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return "redirect:/usuarios/principal";
    }

    // Método para mostrar el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Método para procesar el formulario de registro
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.registrar(usuario); // Implementa este método en tu servicio
        return "redirect:/login"; // Redirige a la página de inicio de sesión después del registro
    }
}

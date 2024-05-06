package com.crud.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.proyecto.IService.UsuarioService;
import com.crud.proyecto.entity.Usuario;


// Controlador para el registro de usuarios
@Controller
public class RegistroController {
    
    @Autowired
    private UsuarioService usuarioService;

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

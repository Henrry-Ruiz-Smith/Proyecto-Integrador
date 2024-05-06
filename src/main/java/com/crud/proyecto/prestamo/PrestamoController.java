package com.crud.proyecto.prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Controlador para la solicitud de préstamos
@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoServiceImpl prestamoService;

    // Método para mostrar el formulario de solicitud de préstamo
    @GetMapping("/solicitar")
    public String mostrarFormularioSolicitud(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        return "solicitud_prestamo";
    }

    // Método para procesar la solicitud de préstamo
    @PostMapping("/solicitar")
    public String solicitarPrestamo(@ModelAttribute("prestamo") Prestamo prestamo) {
        prestamoService.solicitar(prestamo); // Implementa este método en tu servicio
        return "redirect:/usuarios/principal"; // Redirige a la página principal después de la solicitud de préstamo
    }
}

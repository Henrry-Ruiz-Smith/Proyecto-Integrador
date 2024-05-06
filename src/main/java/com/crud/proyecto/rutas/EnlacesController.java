package com.crud.proyecto.rutas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EnlacesController {
    @GetMapping("/")
    public String mostrarFormularioLogin() {
        return "login";
    }
    
	@GetMapping("/vistaInversionista")
	public String verVistaInversionista() {
		return "vistaInversionista";
	}

    @GetMapping("/vistaJefePrestamista")
	public String verVistaJefePrestamista() {
		return "vistaJefePrestamista";
	}

    @GetMapping("/vistaPrestamista")
	public String verVistaPrestamista() {
		return "vistaPrestamista";
	}
    
    @GetMapping("/vistaPrestatario")
	public String verVistaPrestatario() {
		return "vistaPrestatario";
	}
}

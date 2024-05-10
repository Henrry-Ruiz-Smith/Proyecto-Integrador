package com.crud.proyecto.enlaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.crud.proyecto.usuario.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnlacesController {
	@GetMapping("/")
	public String mostrarFormularioLogin() {
		return "login";
	}

	@GetMapping("/vistaInversionista")
	public String verVistaInversionista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaInversionista";
	}

	@GetMapping("/vistaJefePrestamista")
	public String verVistaJefePrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaJefePrestamista";
	}

	@GetMapping("/vistaPrestamista")
	public String verVistaPrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaPrestamista";
	}

	@GetMapping("/vistaPrestatario")
	public String verVistaPrestatario(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "vistaPrestatario";
	}

	// Registros
	@GetMapping("/registrarJefePrestamista")
	public String registrarJefePrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "registrarJefePrestamista";
	}

	// Registros
	@GetMapping("/registrarPrestamista")
	public String registrarPrestamista(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "registrarPrestamista";
	}

	@GetMapping("/registrarPrestatario")
	public String registrarPrestatario(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "registrarPrestatario";
	}

	@GetMapping("/singup")
	public String registrarLoginPrestatario() {

		return "registrarLoginPrestatario";
	}

	@GetMapping("/solicitarPrestamo")
	public String solicitarPrestamos(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "solicitarPrestamo";
	}

	@GetMapping("/aprobarSolicitudesPrestamo")
	public String aprobarSolicitudesPrestamo(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/?error";
		}
		return "aprobarSolicitudesPrestamo";
	}

}
